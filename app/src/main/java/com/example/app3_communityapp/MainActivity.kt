package com.example.app3_communityapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.app3_communityapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() { //'메인' 액티비티
    //바인딩 설정
    lateinit var mainActivityBinding : ActivityMainBinding
    // 프래그먼트 담을 변수 선언
    lateinit var currentFragment : Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)
        //프래그먼트 컨트롤 메소드
        fragmentController("login", false, false)
    }

    //이 메인 액티비티가 관리할 프래그먼트 화면들을 컨트롤할 메소드
    // (프래그먼트 이름/ 백 스택에 추가 여부 / 애니메이션 동작 사용 여부)
    fun fragmentController(name:String, add:Boolean, animate:Boolean) {

        //1) 띄울 프래그먼트 이름받아서 생성
        when(name) {
            "login" -> {
                currentFragment = LoginFragment()
            }
            "join" -> {
                currentFragment = JoinFragment()
            }
            "nick_name" -> {
                currentFragment = NickNameFragment()
            }
        }
        //트랜잭션으로 띄울 현재의 프래그먼트 띄움
        val trans = supportFragmentManager.beginTransaction()
        trans.replace(R.id.main_container, currentFragment)

        //2) 백 스택에 추가 여부 따라서 ("뒤돌아가기 기능"동작 위해서 사용) - T: 뒤로가기 F:종료
        if(add == true) {
            trans.addToBackStack(name)
        }

        //3) 애니메이션 사용 여부에 따라서
        if(animate == true) {
            trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        }

        trans.commit() // 프래그먼트 동적 제어 - 화면에 위의 상황을 적용하는 메소드

    }

}