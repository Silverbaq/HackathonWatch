package dk.w4.hackathonwatch.ui.hackathon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dk.w4.hackathonwatch.R
import dk.w4.hackathonwatch.ui.ScopedFragment
import kotlinx.android.synthetic.main.hackathon_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class HackathonFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()

    private val viewModelFactory: HackathonViewModelFactory by instance()
    private lateinit var viewModel: HackathonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.hackathon_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(HackathonViewModel::class.java)

        bindUI()
    }

    private fun bindUI() = launch {
        val hackathons = viewModel.hackathons.await()
        hackathons.observe(this@HackathonFragment, Observer {
            var text = ""

            for (h in it){
                text += "${h.name} \n"
            }

            textView.text = text
        })
    }

}
