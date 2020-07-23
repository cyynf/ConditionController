package cpf.conditioncontroller.demo

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cpf.conditioncontroller.ConditionController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ConditionController()
            .add(phone) {
                // 手机号码必须为13位
                it.length() == 13
            }
            .add(password)
            .add(agreement)
            .bind(
                target = submit,
                success = {
                    it.setBackgroundColor(Color.GREEN)
                },
                failure = {
                    it.setBackgroundColor(Color.RED)
                })
    }

    fun login(v: View) {
        Toast.makeText(this, "login", Toast.LENGTH_SHORT).show()
    }
}