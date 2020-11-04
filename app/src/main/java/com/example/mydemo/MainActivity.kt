package com.example.mydemo

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.common.ducis.network.RetrofitUtils
import kotlinx.android.synthetic.main.activity_main.*
import com.example.mydemo.MyApi


class MainActivity : AppCompatActivity() {

    //NFC对象
    private var mNfcAdapter: NfcAdapter? = null
    private var pi: PendingIntent? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //获取默认的NFC控制器
        //初始化NfcAdapter
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this)
        if (mNfcAdapter == null) {
            Toast.makeText(this@MainActivity, "设备不支持NFC!", Toast.LENGTH_LONG).show()
            finish()
            return
        }
        if (!mNfcAdapter?.isEnabled!!) {
            Toast.makeText(this@MainActivity, "请在系统设置中先启用NFC功能!", Toast.LENGTH_LONG).show()
            finish()
            return
        }
        //初始化PendingIntent
        // 初始化PendingIntent，当有NFC设备连接上的时候，就交给当前Activity处理
        pi = PendingIntent.getActivity(
            this, 0, Intent(this, javaClass)
                .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0
        )

        tv_get_card.setOnClickListener {

        }
    }

    //获取数据
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        // 当前app正在前端界面运行，这个时候有intent发送过来，那么系统就会调用onNewIntent回调方法，将intent传送过来
        // 我们只需要在这里检验这个intent是否是NFC相关的intent，如果是，就调用处理方法
        if (NfcAdapter.ACTION_TAG_DISCOVERED == intent.action) {
            processIntent(intent)
        }
    }

    //启动
    override fun onResume() {
        super.onResume()
        mNfcAdapter!!.enableForegroundDispatch(this, pi, null, null)
    }

    //解析
    private fun processIntent(intent: Intent) {
        //取出封装在intent中的TAG
        val tagFromIntent: Tag? = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)
        val CardId = ByteArrayToHexString(tagFromIntent?.id!!)
        tv_button.text = CardId
        Toast.makeText(this@MainActivity, CardId, Toast.LENGTH_LONG).show()
    }

    //转为16进制字符串
    private fun ByteArrayToHexString(inarray: ByteArray): String {
        var i: Int
        var j: Int
        var `in`: Int
        val hex = arrayOf(
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A",
            "B", "C", "D", "E", "F"
        )
        var out = ""
        j = 0
        while (j < inarray.size) {
            `in` = inarray[j].toInt() and 0xff
            i = `in` shr 4 and 0x0f
            out += hex[i]
            i = `in` and 0x0f
            out += hex[i]
            ++j
        }
        return out
    }

}