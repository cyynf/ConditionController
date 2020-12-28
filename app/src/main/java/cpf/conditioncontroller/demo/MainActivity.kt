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
            .add(phone) { it.length() == 11 }
            .add(password)
            .add(agreement)
            .bind(submit) {
                submit.isEnabled = it
                if (it) {
                    submit.setBackgroundColor(Color.GREEN)
                } else {
                    submit.setBackgroundColor(Color.RED)
                }
            }
            .commit()
    }

    fun login(v: View) {
        Toast.makeText(this, "login", Toast.LENGTH_SHORT).show()
    }
}