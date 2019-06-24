package dictionary.etymology.alphabet.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.PopupWindow
import android.widget.Toast
import dictionary.etymology.alphabet.adapter.DescriptionAdapter
import dictionary.etymology.alphabet.adapter.SearchAdapter
import dictionary.etymology.alphabet.database.DataBaseUtil
import dictionary.etymology.alphabet.database.databean.WordBean
import dictionary.etymology.alphabet.etymologydictionary.R
import kotlinx.android.synthetic.main.activity_main.*
import java.io.FileOutputStream
import java.io.InputStream


class MainActivity : AppCompatActivity(), SearchAdapter.OnItemClickListener {

    val dataBaseUtil = DataBaseUtil()
    private lateinit var popView: PopupWindow
    private lateinit var searchAdapter: SearchAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        setContentView(R.layout.activity_main)
        copyDBToDatabases()
        initData()
    }

    private fun initData() {
        initRecyclerview()
        etInput.addTextChangedListener(
                object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {

                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        if (clickFlag) {
                            clickFlag = false
                            return
                        }
                        if (TextUtils.isEmpty(p0.toString()))
                            return
                        if (rvResult.visibility != View.VISIBLE)
                            rvResult.visibility = View.VISIBLE
//                        createPopupWindow()
                        var matchedWords: MutableList<WordBean> = dataBaseUtil.getMatchedWords(this@MainActivity, p0.toString().trim())
                        searchAdapter.setData(matchedWords)
                    }

                })
        btSearch.setOnClickListener {
            var wordStr = etInput.text.toString().trim()
            search(wordStr)
        }

    }

    private fun search(wordStr: String) {
        rvResult.visibility = View.GONE
        var description: String
        if (TextUtils.isEmpty(wordStr)) {
            description = "Please input valid word!"
            setDescription(description)
        }
        var matchedWords: MutableList<WordBean> = dataBaseUtil.getMatchedWords(this, wordStr)
        if (matchedWords == null || matchedWords.isEmpty()) {
            description = "No Record!"
            setDescription(description)
        }
        descrpitionAdapter.setData(matchedWords,wordStr)
        if (tvDescription.visibility == View.VISIBLE)
            tvDescription.visibility = View.GONE
    }

    private fun setDescription(description: String) {
        tvDescription.text = description
        if (tvDescription.visibility != View.VISIBLE)
            tvDescription.visibility = View.VISIBLE
        return
    }

    private var clickFlag: Boolean = false

    override fun onItemClick(wordStr: String) {
        clickFlag = true
        search(wordStr.trim())
        etInput.setText(wordStr)
    }

    private lateinit var descrpitionAdapter: DescriptionAdapter

    private fun initRecyclerview() {
        rvResult.layoutManager = LinearLayoutManager(this)
        searchAdapter = SearchAdapter(this)
        rvResult.adapter = searchAdapter
        searchAdapter.setOnItemClickListener(this)
        rvDescription.layoutManager = LinearLayoutManager(this)
        descrpitionAdapter = DescriptionAdapter(this)
        rvDescription.adapter = descrpitionAdapter
    }


    private var created: Boolean = false

    fun dip2px(dpValue: Float): Int {
        val scale = resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    private fun createPopupWindow() {
        if (!created) {
            var view = LayoutInflater.from(this).inflate(R.layout.search_popupwindow, null)
            popView = PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, dip2px(200f))
            popView.isFocusable = true
            popView.isOutsideTouchable = true
            popView.inputMethodMode = PopupWindow.INPUT_METHOD_NEEDED
            popView.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;
            var rvSearch = view.findViewById<RecyclerView>(R.id.rvSearch)
            rvSearch.layoutManager = LinearLayoutManager(this)
            searchAdapter = SearchAdapter(this)
            rvSearch.adapter = searchAdapter
        }
        created = true
        if (!popView.isShowing)
            popView.showAsDropDown(etInput)
    }

    private fun copyDBToDatabases() {
        val dbPath = getDatabasePath("etymology.db")
        if (dbPath.exists()) {
            return
        }
        var inputStream: InputStream? = null
        var outputStream: FileOutputStream? = null
        try {
            inputStream = resources.openRawResource(R.raw.etymology)
            outputStream = FileOutputStream(dbPath)
            val buffer = ByteArray(10240)
            var length: Int
            var flag = true
            while (flag) {
                length = inputStream.read(buffer)
                if (length > 0) {
                    outputStream.write(buffer, 0, length)
                } else {
                    flag = false
                }
            }
        } catch (e: Exception) {
            var e1 = e
        } finally {
            outputStream?.flush()
            outputStream?.close()
            inputStream?.close()
            Toast.makeText(this, "数据导入成功!", Toast.LENGTH_SHORT)
        }

    }
}
