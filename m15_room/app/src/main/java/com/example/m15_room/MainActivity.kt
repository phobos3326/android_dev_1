import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.m15_room.databinding.ActivityMainBinding
import com.example.m15_room.ui.main.App
import com.example.m15_room.ui.main.MainViewModel
import com.example.m15_room.ui.main.State
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val wordDao = (application as App).db.wordDao()
                return MainViewModel(wordDao = wordDao) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenCreated {
            viewModel.allWords.collect {
                binding.textView.text = it.joinToString(separator = "\r\n")
            }
        }


        binding.editText.addTextChangedListener {
            lifecycleScope.launchWhenCreated {
                viewModel.insertWord = it.toString()
                viewModel.getWordMatches()?.collect {
                    it.joinToString(separator = "\r\n")
                }
            }
        }

        /*      binding.editText.addTextChangedListener(object : TextWatcher {
                  override fun afterTextChanged(s: Editable?) {
                  }

                  override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                  }

                  override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                      lifecycleScope.launch {
                          viewModel.getWordMatches()?.collect {
                              it.joinToString(separator = "\r\n")
                          }
                      }

                  }
              })*/

        binding.button.setOnClickListener { viewModel.onAddBtn() }
        binding.buttonDell.setOnClickListener { viewModel.onDeleteButton() }


        binding.buttonCheck.setOnClickListener {
            lifecycleScope.launch {
                viewModel.getWordMatches()?.collect {
                    binding.textView.text = it.joinToString(separator = "\r\n")
                }
            }

        }



        lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                when (it) {
                    State.Start -> {
                        viewModel.allWords.collect {
                            binding.textView.text = it.joinToString(separator = "\r\n")
                            Log.d("state", "Star")

                        }
                    }
                    State.Clear -> {
                        viewModel.allWords.collect {
                            binding.textView.text = it.joinToString(separator = "\r\n")
                            Log.d("state", "Clear")
                        }
                    }
                    State.Matches -> {
                        viewModel.getWordMatches()?.collect() {
                            binding.textView.text = it.joinToString(separator = "\r\n")
                            Log.d("state", "Matches")
                        }
                    }
                }
            }
        }


        /*    fun getItemsFromDb(searchText: String) {
                var searchText = searchText
                searchText = "%$searchText%"

                viewModel.getWordMatches()?.observe(this@MainActivity) { list ->
                    list?.let {
                        Log.e("List = ", list.toString())
                    }

                }

            }*/

        /*  lifecycleScope.launchWhenCreated {
              viewModel.getGetWordMatches().collect {

                  Log.d("TAG", it.toString())
              }
          }*/


    }
}