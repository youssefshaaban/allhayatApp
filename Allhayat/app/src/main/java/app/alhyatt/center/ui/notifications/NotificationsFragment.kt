package app.alhyatt.center.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import app.alhyatt.center.R

import app.alhyatt.center.base.BaseFragment
import app.alhyatt.center.base.BaseViewModel
import app.alhyatt.center.databinding.FragmentNotificationsBinding
import app.alhyatt.center.utilis.AppUtils
import app.alhyatt.center.utilis.ambuklance

class NotificationsFragment : BaseFragment<FragmentNotificationsBinding>() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    lateinit var binding: FragmentNotificationsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding=getViewDataBinding()
        binding.recycleNotification.layoutManager=LinearLayoutManager(activity)
        binding.recycleNotification.adapter= NotificationAdapter()
        binding.call.setOnClickListener {
            AppUtils.openDual(binding.root
                .context, ambuklance)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_notifications
    }

    override fun getViewModel(): BaseViewModel? {
        return null
    }
}