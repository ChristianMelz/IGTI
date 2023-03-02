package br.com.igti.blog_aula212.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.igti.blog_aula212.models.Blog

class MainViewModel: ViewModel() {
    var lst = MutableLiveData<ArrayList<Blog>>()
    var newList = arrayListOf<Blog>()

    fun adicionar(blog: Blog){
        newList.add(blog)
        lst.value = newList
    }

    fun remove(blog: Blog) {
        newList.remove(blog)
        lst.value = newList
    }

}
