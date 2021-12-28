 # ContentProvider的作用
 (1)一种是使用现有的ContentProvider读取和操作相应程序中的数据
 (2)另一种是创建自己的ContentProvider，给 程序的数据提供外部访问接口

 ## ContentProvider的基本用法
 (1)获取ContentResolver类，可以通过Context中的getContentResolver()方法获取该类的实例
 (2)通过ContentResolver对象对数据进行增删改查操作：insert()-- 添加数据&update()-- 用于更新数据&delete()--用于删除数据&query()--用于查询数据

 有没有似曾相识的感觉?没错， SQLiteDatabase中也是使用这几个方法进行增删改查操作的，只不过它们 在方法参数上稍微有一些区别。
 不同于SQLiteDatabase，ContentResolver中的增删改查方法都是不接收表 名参数的，而是使用一个Uri参数代替，这个参数被称为内容URI
 内容URI 给ContentProvider中的数据建立了唯一标识符，它主要由两部分组成: authority和path

 authority:一般为程序的包名
 path：则是用于对同一应用程序中不同的表做 区分的，通常会添加到authority的后面。比如某个应用的数据库里存在两 张表table1和table2

 eg:
 com.example.app.provider/table1
 com.example.app.provider/table2

 ⚠️：获取到了内容URI后还需将它解析成Uri对象，解析方法为：
  val uri = Uri.parse("content://com.example.app.provider/table1")

 ## query()查询table1中的数据
 val cursonr=contentResolver.query(uri,projection,selection,selectionArgs,sortOrder)
 参数详解：
 query()方法参数                    对应SQL部分                               描述
 uri                             from table_name                  指定查询某个应用程序下的某一张表
 projection                      select column1, column2          指定查询的列名
 selection                       where column = value             指定where的约束条件
 selectionArgs                    ...                             为where中的占位符提供具体的值
 sortOrder                       order by column1, column2        指定查询结果的排序方式

 查询完成后返回的仍然是一个Cursor对象，这时我们就可以将数据从 Cursor对象中逐个读取出来了。
 读取的思路仍然是通过移动游标的位置遍 历Cursor的所有行，然后取出每一行中相应列的数据，代码如下所示:
 ```
 while(crusor.moveToNext()){
      val column1=cursor.getString(cursor.getColumnIndex("column1"))
      val column2=cursor.getString(cursor.getColumnIndex("column2"))
 }
 ```
 ## insert()
  val values = contentValuesOf("column1" to "text", "column2" to 1) contentResolver.insert(uri, values)
  将待添加的数据组装到ContentValues中，然后调用 ContentResolver的insert()方法，将Uri和ContentValues作为参数 传入即可。

 ## update()
 val values = contentValuesOf("column1" to "") contentResolver.update(uri, values, "column1 = ? and column2 = ?", arrayOf("text", "1"))
 如果我们想要更新这条新添加的数据，把column1的值清空，可以借助 ContentResolver的update()方法实现，代码如下所示:
 上述代码使用了selection和selectionArgs参数来对想要更新 的数据进行约束，以防止所有的行都会受影响。

 ##delete()
 contentResolver.delete(uri, "column2 = ?", arrayOf("1"))




----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 # 创建ContentProvider的步骤
 前面已经提到过，如果想要实现跨程序共享数据的功能，可以通过新建一 个类去继承ContentProvider的方式来实现。

 ## 重写以下六个方法：
 * onCreate(): 初始化ContentProvider的时候调用。通常会在这里完 成对数据库的创建和升级等操作，返回true表示ContentProvider初始化 成功，返回false则表示失败。
 * query(): 从ContentProvider中查询数据。uri参数用于确定查询哪 张表，projection参数用于确定查询哪些列，selection和 selectionArgs参数用于约束查询哪些行，sortOrder参数用于对结果 进行排序，查询的结果存放在Cursor对象中返回。
 * insert(): 向ContentProvider中添加一条数据。uri参数用于确定 要添加到的表，待添加的数据保存在values参数中。添加完成后，返回一 个用于表示这条新记录的URI。
 * update(): 更新ContentProvider中已有的数据。uri参数用于确定 更新哪一张表中的数据，新数据保存在values参数中，selection和 selectionArgs参数用于约束更新哪些行，受影响的行数将作为返回值返 回。
 * delete(): 从ContentProvider中删除数据。uri参数用于确定删除 哪一张表中的数据，selection和selectionArgs参数用于约束删除哪 些行，被删除的行数将作为返回值返回。
 * getType(): 根据传入的内容URI返回相应的MIME类型。

 ## 我们知道一个标准的URI写法是：
 content://com.example.app.provider/table1
 除此之外，我们还可以在这个内容URI的后面加上一个id，例如:
 content://com.example.app.provider/table1/1

 内容URI的格式主要就只有以上两种，以路径结尾表示期望访问该表中所有 的数据，以id结尾表示期望访问该表中拥有相应id的数据。
 我们可以使用 通配符分别匹配这两种格式的内容URI，规则如下。
 (1)*表示匹配任意长度的任意字符。
 (2)#表示匹配任意长度的数字。

 所以，一个能够匹配任意表的内容URI格式就可以写成:
 content://com.example.app.provider/*

 一个能够匹配table1表中任意一行数据的内容URI格式就可以写成:
  content://com.example.app.provider/table1/#

 ## UriMatcher
 UriMatcher这个类就可以轻松地实现匹配内容URI的功 能。UriMatcher中提供了一个addURI()方法，这个方法接收3个参数，
 可以分别把authority、path和一个自定义代码传进去。这样，当调用 UriMatcher的match()方法时，就可以将一个Uri对象传入，
 返回值是某个能够匹配这个Uri对象所对应的自定义代码，利用这个代码，我们就可 以判断出调用方期望访问的是哪张表中的数据了.
```
  private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
    init {
        uriMatcher.addURI("com.jimmy.androidproject", "table1", table1Dir)
        uriMatcher.addURI("com.jimmy.androidproject", "table1/#", table1Item)
        uriMatcher.addURI("com.jimmy.androidproject", "table2", table2Dir)
        uriMatcher.addURI("com.jimmy.androidproject", "table2/#", table2Item)
    }
```
## query()方法
当 query()方法被调用的时候，就会通过UriMatcher的match()方法对传 入的Uri对象进行匹配，如果发现UriMatcher中某个内容URI格式成功匹 配了该Uri对象，
则会返回相应的自定义代码，然后我们就可以判断出调用 方期望访问的到底是什么数据了。
```
override fun query(uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor? {
        when (uriMatcher.match(uri)) {
            table1Dir -> {
                // 查询table1表中的所有数据
            }
            table1Item -> {
                // 查询table1表中的单条数据
            }
            table2Dir -> {
                // 查询table2表中的所有数据
            }
            table2Item -> {
                // 查询table2表中的单条数据
            }
        }
        return null
    }
```
insert()、 update()、delete()这几个方法的实现是差不多的，它们都会携带uri 这个参数，然后同样利用UriMatcher的match()方法判断出调用方期望 访问的是哪张表，再对该表中的数据进行相应的操作就可以了。

## getType()方法
它是所 有的ContentProvider都必须提供的一个方法，用于获取Uri对象所对应的 MIME类型。一个内容URI所对应的MIME字符串主要由3部分组成，
Android 对这3个部分做了如下格式规定。
* 必须以vnd开头。
* 如果内容URI以路径结尾，则后接android.cursor.dir/;如果内 容URI以id结尾，则后接android.cursor.item/。
* 最后接上vnd.<authority>.<path>。

所以，对于content://com.example.app.provider/table1这个内容URI， 它所对应的MIME类型就可以写成:
 vnd.android.cursor.dir/vnd.com.example.app.provider.table1

对于content://com.example.app.provider/table1/1这个内容URI，它所 对应的MIME类型就可以写成:
 vnd.android.cursor.item/vnd.com.example.app.provider.table1

⚠️：那么，如何才能保 证隐私数据不会泄漏出去呢?其实多亏了ContentProvider的良好机制，这 个问题在不知不觉中已经被解决了。
因为所有的增删改查操作都一定要匹 配到相应的内容URI格式才能进行，而我们当然不可能向UriMatcher中添加隐私数据的URI，所以这部分数据根本无法被外部程序访问，安全问题也 就不存在了。

# 实例：ContentProvider实现跨程序数据共享
简单起见，我们还是在上一章中DatabaseTest项目的基础上继续开发，通 过ContentProvider来给它加入外部访问接口。
* 创建一个ContentProvider， 右击com.jimmy.databasetest包→New→Other→Content Provider
* 指定authority路径
* 勾选Exported //属性表示是否允许外部程序访问我们的ContentProvider
* Enabled   //属性表示是否启用这个ContentProvider



