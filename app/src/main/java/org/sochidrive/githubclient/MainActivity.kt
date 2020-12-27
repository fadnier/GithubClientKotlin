package org.sochidrive.githubclient

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.sochidrive.githubclient.mvp.presenter.Presenter
import org.sochidrive.githubclient.mvp.view.MainView

class MainActivity : AppCompatActivity(), MainView {
    private val presenter = Presenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        btn_counter1.text = presenter.getCurrent1().toString()
        btn_counter2.text = presenter.getCurrent2().toString()
        btn_counter3.text = presenter.getCurrent3().toString()

        btn_counter1.setOnClickListener {presenter.counterClick1()}
        btn_counter2.setOnClickListener {presenter.counterClick2()}
        btn_counter3.setOnClickListener {presenter.counterClick3()}
    }

    override fun setButtonText1(text: String) {
        btn_counter1.text = text
    }

    override fun setButtonText2(text: String) {
        btn_counter2.text = text
    }

    override fun setButtonText3(text: String) {
        btn_counter3.text = text
    }
}