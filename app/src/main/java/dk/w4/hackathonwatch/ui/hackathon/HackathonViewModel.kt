package dk.w4.hackathonwatch.ui.hackathon

import androidx.lifecycle.ViewModel;
import dk.w4.hackathonwatch.data.repository.HackathonRepository
import dk.w4.hackathonwatch.internal.lazyDeferred

class HackathonViewModel(
    private val hackathonRepository: HackathonRepository
) : ViewModel() {
    val hackathons by lazyDeferred {
        hackathonRepository.getHackathons()
    }
}
