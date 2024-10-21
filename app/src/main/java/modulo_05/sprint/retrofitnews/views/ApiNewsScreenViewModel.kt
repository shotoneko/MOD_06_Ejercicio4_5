package modulo_05.sprint.retrofitnews.views

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import modulo_05.sprint.retrofitnews.repository.NewsRepository
import javax.inject.Inject

@HiltViewModel
class ApiNewsScreenViewModel @Inject constructor(repository: NewsRepository) :ViewModel() {

    val articles = repository.getAllNews()

}