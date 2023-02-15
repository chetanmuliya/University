package cm.chettas.university.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cm.chettas.university.model.data.UniversitiesResponseItem
import cm.chettas.university.repository.MainRepository
import cm.chettas.university.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel(){

    private val query = MutableStateFlow("")

    private var filterList = listOf<UniversitiesResponseItem>()

    var queryText: String?
        get() = query.value
        set(value) {
            if (value != null) {
                query.value = value
            }
        }

    private val _res = MutableLiveData<Resource<List<UniversitiesResponseItem?>>>()

    val res : LiveData<Resource<List<UniversitiesResponseItem?>>>
        get() = _res

    init {
        getUniversities()
    }

    private fun getUniversities() = viewModelScope.launch {
        _res.postValue(Resource.Loading())
        mainRepository.getUniversities().let {
            if (it.isSuccessful){
                _res.postValue(Resource.Success(it.body()))
                it.body()?.let { list -> filterList = list }
            }else{
                _res.postValue(Resource.Error(it.errorBody().toString()))
            }
        }
    }

    val filteredCategories = query
        .debounce(200) // low debounce because we are just filtering local data
        .distinctUntilChanged()
        .map {
        val criteria = it.lowercase()
        filterList.filter {
                university -> criteria in university.name.lowercase()
        }
    }
}