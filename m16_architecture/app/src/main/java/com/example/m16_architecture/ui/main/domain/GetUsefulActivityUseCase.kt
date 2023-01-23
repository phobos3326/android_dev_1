package com.example.m16_architecture.ui.main.domain



import com.example.m16_architecture.ui.main.data.UsefulActivitiesRepository
import com.example.m16_architecture.ui.main.entity.UsefulActivity
import javax.inject.Inject


class GetUsefulActivityUseCase  {

    private val repository  = UsefulActivitiesRepository()

  suspend  fun execute(): UsefulActivity {
        return repository.retrofitInstanse().getUsefulActivity()
    }
}