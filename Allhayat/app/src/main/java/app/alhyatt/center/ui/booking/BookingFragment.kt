package app.alhyatt.center.ui.booking


import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams

import androidx.recyclerview.widget.LinearLayoutManager

import app.alhyatt.center.R

import app.alhyatt.center.base.BaseFragment
import app.alhyatt.center.base.BaseViewModel
import app.alhyatt.center.databinding.BookingFragmentBinding
import app.alhyatt.center.utilis.AppUtils
import app.alhyatt.center.utilis.ambuklance


class BookingFragment : BaseFragment<BookingFragmentBinding>() {

    lateinit var binding: BookingFragmentBinding
    companion object {
        fun newInstance() = BookingFragment()
    }

    private lateinit var viewModel: BookingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=getViewDataBinding()
        binding.tabs.addTab(binding.tabs.newTab().setText(getString(R.string.upcoming)))
        binding.tabs.addTab(binding.tabs.newTab().setText(getString(R.string.past)))
        binding.recycle.layoutManager=LinearLayoutManager(activity)
        binding.recycle.adapter= AppointmentAdapter()
         setMarginBetweenTabs()

        binding.call.setOnClickListener {
            AppUtils.openDual(binding.root
                .context, ambuklance
            )
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun getLayoutId(): Int {
        return R.layout.booking_fragment
    }

    override fun getViewModel(): BaseViewModel? {
        return null
    }

    private fun setMarginBetweenTabs() {
        for (i in 0 until binding.tabs.tabCount - 1) {
            val tab = (binding.tabs.getChildAt(0) as ViewGroup).getChildAt(i)
            val p = tab.layoutParams as MarginLayoutParams
            p.setMargins(0, 0, 50, 0)
            tab.requestLayout()
        }
    }

}