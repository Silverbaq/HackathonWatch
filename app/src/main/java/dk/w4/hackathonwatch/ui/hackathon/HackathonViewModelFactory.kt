package dk.w4.hackathonwatch.ui.hackathon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dk.w4.hackathonwatch.data.repository.HackathonRepository


class HackathonViewModelFactory(
    private val hackathonRepository: HackathonRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HackathonViewModel(hackathonRepository) as T
    }
}