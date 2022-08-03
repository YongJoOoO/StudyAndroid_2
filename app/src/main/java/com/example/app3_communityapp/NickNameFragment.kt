package com.example.app3_communityapp

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.example.app3_communityapp.databinding.FragmentNickNameBinding

class NickNameFragment : Fragment() { // '닉네임 입력' 프래그먼트
    //바인딩 설정
    lateinit var nickNameFragmentBinding : FragmentNickNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        nickNameFragmentBinding = FragmentNickNameBinding.inflate(inflater)
        // -> 툴바 제목 설정
        nickNameFragmentBinding.nicknameToolbar.title = "닉네임 설정"
        // 백 스택에 저장된 프래그먼트들을 완전히 종료하기 위함
        // 로그인 시도가 완료된 뒤에는 '뒤로가기' 를 눌러도 액티비티가 종료되어야 한다.
        // -> 닉네임 설정 버튼 클릭 시 -> 새로운 주기를 갖는 메인 액티비티를 다시 띄워주어야 함
        nickNameFragmentBinding.nicknameJoinBtn.setOnClickListener {
            //사용자 입력값 가져오기
            val nickNameNickName = nickNameFragmentBinding.nicknameNickname.text.toString()
            //유효성 검사
            if(nickNameNickName == null || nickNameNickName.length == 0) {
                val dialogBuilder = AlertDialog.Builder(requireContext())
                dialogBuilder.setTitle("닉네임 입력 오류")
                dialogBuilder.setMessage("닉네임을 입력해주세요")
                dialogBuilder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                    nickNameFragmentBinding.nicknameNickname.requestFocus()
                }
                dialogBuilder.show()
                return@setOnClickListener
            }

            //사용자가 여기서 입력한 '닉네임' 데이터도 다시 서버로 보낼 용도로 여기서 담아준다.
            val act = activity as MainActivity
            act.userNickName = nickNameNickName

            Log.d("test", "${act.userId}")
            Log.d("test", "${act.userPw}")
            Log.d("test", "${act.userNickName}")

            val mainIntent = Intent(requireContext(), MainActivity::class.java)
            startActivity(mainIntent)
            activity?.finish() //기존 액티비티 종료
        }

        return nickNameFragmentBinding.root
    }

}