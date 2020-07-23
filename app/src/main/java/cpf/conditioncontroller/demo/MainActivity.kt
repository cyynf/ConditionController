package cpf.conditioncontroller.demo

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cpf.conditioncontroller.ConditionController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ConditionController<TextView>()
            .add(phone)
            .add(password)
            .add(agreement)
            .bind(submit)
    }

    fun login(v: View) {
        Toast.makeText(this, "login", Toast.LENGTH_SHORT).show()
    }
}