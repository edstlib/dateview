package id.co.edtslib.dateview

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.view.Window
import android.widget.DatePicker
import androidx.appcompat.widget.AppCompatTextView
import java.text.SimpleDateFormat
import java.util.*

class DateView: AppCompatTextView {
    private var maxDate = 0L
    private var minDate = 0L
    private var format = "dd/MM/yyyy"
    private var time = Date()

    var delegate: DateDelegate? = null

    class DateDialog(context: Context, time: Date, private val minDate: Long,
                     private val maxDate: Long,
        private val action: (date: Date) -> Unit):
        Dialog(context, R.style.Animation_Design_BottomSheetDialog) {
        private var datePicker: DatePicker? = null
        private var lTime = Date()

        init {
            lTime = time
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            requestWindowFeature(Window.FEATURE_NO_TITLE)

            setContentView(R.layout.dialog_date_view)
            window?.decorView?.setBackgroundResource(android.R.color.transparent)

            val calendar = Calendar.getInstance()
            calendar.time = lTime

            datePicker = findViewById(R.id.datePicker)
            datePicker?.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE)
            ) { _, year, month, day ->
                val calendar = Calendar.getInstance()

                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, day)

                lTime = calendar.time
            }
            if (minDate > 0) {
                datePicker?.minDate = minDate
            }

            if (maxDate > 0) {
                datePicker?.maxDate = maxDate
            }

            findViewById<View>(R.id.tvOK).setOnClickListener {
                dismiss()
                action(lTime)
            }
            findViewById<View>(R.id.tvCancel).setOnClickListener {
                dismiss()
            }
        }
    }

    constructor(context: Context) : super(context) {
        init(null)
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        setOnClickListener {
            val dialog = DateDialog(context, time, minDate, maxDate) {
                time = it

                val dateFormat = SimpleDateFormat(format, Locale.getDefault())
                text = dateFormat.format(it)

                delegate?.onChanged(time)
            }
            dialog.show()
        }

        if (attrs != null) {
            val a = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.DateView,
                0, 0
            )

            val lFormat = a.getString(R.styleable.DateView_dateViewFormat)
            if (lFormat != null) {
                format = lFormat
            }

            a.recycle()
        }

    }

    fun updateDate(year: Int, month: Int, day: Int) {
        val calendar = Calendar.getInstance()

        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)

        time = calendar.time
    }

    fun setMaxDate(maxDate: Long) {
        this.maxDate = maxDate
    }

    fun setMinDate(minDate: Long) {
        this.minDate = minDate
    }
}