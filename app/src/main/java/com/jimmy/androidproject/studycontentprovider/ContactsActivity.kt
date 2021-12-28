package com.jimmy.androidproject.studycontentprovider

import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.jimmy.androidproject.R
import com.jimmy.androidproject.adapter.ContactsAdapter
import com.jimmy.androidproject.bean.ContactsInfo
import com.jimmy.androidproject.databinding.ActivityContactsBinding
import com.jimmy.androidproject.inflate

/**
 * @Description:    获取联系人列表
 * @Author:         zhangchun
 * @CreateDate:     2021/7/18
 * @Version:        1.0
 */
class ContactsActivity : AppCompatActivity() {
    private var contactsList=ArrayList<ContactsInfo>()
    private var contactsAdapter: ContactsAdapter? = null
    private val binding by inflate<ActivityContactsBinding>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        contactsAdapter=ContactsAdapter(data = contactsList)

        binding.rvContacts.layoutManager=LinearLayoutManager(this)
        binding.rvContacts.adapter=contactsAdapter

        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_CONTACTS)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_CONTACTS),1)
        }else {
            readContacts()
        }


    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1->{
                if (grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    readContacts()
                } else {
                    Toast.makeText(this, "You denied the permission",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    /**
     * 读取联系人列表数据
     */
    private fun readContacts() {
        contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null)?.apply {
            while (moveToNext()){
                //获取联系人姓名
                val displayName=getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                //获取联系人手机号
                val number=getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                contactsList.add(ContactsInfo().apply {
                    this.name=displayName
                    this.phoneNumber=number
                })
            }
            contactsAdapter?.notifyDataSetChanged()
            close()
        }

    }
}