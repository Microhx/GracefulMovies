package com.xw.project.gracefulmovies.view

import android.content.Context
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.xw.project.gracefulmovies.R

/**
 * author: Java
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2019/5/21
 *
 * version : 1.0.1
 *
 * desc : 封装的搜索界面框 左边有显示搜索的图标
 *                        中间显示搜索文字
 *                        右边有删除框
 *                        最右边有取消按键
 */
class CustomSearchView(context: Context?, attrs: AttributeSet?) : RelativeLayout(context, attrs), View.OnClickListener {

  constructor(context: Context) : this(context, null)

  private var imageSearchView: ImageView
  private var etSearchContent: EditText
  private var imageDeleteView: ImageView
  private var tvCancel: TextView

  var searchViewInterface: SearchViewInterface?= null

  init {
    val rootView = LayoutInflater.from(context).inflate(R.layout.custom_seach_view, this, true)
    imageSearchView = rootView.findViewById(R.id.id_search_view)
    etSearchContent = rootView.findViewById(R.id.id_et_content)
    imageDeleteView = rootView.findViewById(R.id.id_delete_view)
    tvCancel = rootView.findViewById(R.id.id_tv_cancel)

    etSearchContent.addTextChangedListener(object: TextWatcher {
      override fun afterTextChanged(s: Editable?) {
        Log.d("afterTextChanged","--->$s")

        imageDeleteView.visibility = if(TextUtils.isEmpty(s)) {
              View.INVISIBLE
        }else{
              View.VISIBLE
        }

        searchViewInterface?.gotoSearch(s)
      }

      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })

    imageDeleteView.setOnClickListener(this)
    tvCancel.setOnClickListener(this)
  }

  override fun onClick(v: View) {
    when(v.id) {
      R.id.id_tv_cancel -> {
        searchViewInterface?.onCancel()
      }

      R.id.id_delete_view -> {
        etSearchContent.setText("")
      }
    }
  }


  interface SearchViewInterface {
      fun onCancel()

      fun gotoSearch(s: Editable?)
  }
}
