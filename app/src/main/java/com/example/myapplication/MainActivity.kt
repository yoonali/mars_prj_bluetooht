package com.example.myapplication

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts


class MainActivity : AppCompatActivity() {
    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private val bluetoothManager: BluetoothManager by lazy {
        getSystemService(BluetoothManager::class.java)
    }
    private val bluetoothAdapter: BluetoothAdapter? by lazy {
        bluetoothManager.adapter
    }
    private val activityResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK) {
                showMessage("블루투스 활성화")
            }else if (it.resultCode == RESULT_CANCELED) {
                showMessage("취소")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //BluetoothAdapter가 Null이라면 블루투스를 지원하지 않는 것이므로 종료
        if(bluetoothAdapter == null) {
            showMessage("블루투스를 지원하지 않는 장치입니다.")
            finish()
        }
    }

    //활성화 요청
    fun setActivate() {
        bluetoothAdapter?.let{
            //비활성화 상태라면
            if(!it.isEnabled) {
                //활성화 요청
                val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                activityResultLauncher.launch(intent)
            }else {
                //활성상태라면
                showMessage("이미 활성화 되어 있습니다.")
            }
        }
    }

    //비활성화 요청
    fun setDeActivate() {
        bluetoothAdapter?.let {
            //비활성화 상태라면
            if(!it.isEnabled) {
                showMessage("이미 비활성화 되어있습니다.")
            }else {
                //블루투스 비활성화
                //it.disable() -> 오류 발생
            }
        }
    }

    fun getPairedDevices() {
        bluetoothAdapter?.let {
            //블루투스 활성화 상태라면
            if(it.isEnabled) {
                //ArrayAdapter clear
            }
        }
    }


}