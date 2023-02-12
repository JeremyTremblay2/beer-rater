package fr.iut.beerrater.presentation.add_edit_review

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import fr.iut.beerrater.R
import fr.iut.beerrater.common.Constants.DEFAULT_BEER_ID
import fr.iut.beerrater.common.Constants.NEW_REVIEW_ID
import fr.iut.beerrater.databinding.FragmentAddEditReviewBinding
import fr.iut.beerrater.di.AddEditReviewViewModelFactory
import fr.iut.beerrater.di.provideFactory
import javax.inject.Inject

@AndroidEntryPoint
class AddEditReviewFragment : Fragment() {
    @Inject
    lateinit var addEditReviewViewModelFactory: AddEditReviewViewModelFactory
    private var listener: OnInteractionListener? = null
    private var reviewId: Int = NEW_REVIEW_ID
    private var beerId: Int = DEFAULT_BEER_ID
    private lateinit var addEditReviewViewModel: AddEditReviewViewModel

    companion object {
        private const val REVIEW_ID = "fr.iut.beerrater.presentation.add_edit_review.review_id"
        private const val BEER_ID = "fr.iut.beerrater.presentation.add_edit_review.beer_id"

        fun newInstance(reviewId: Int, beerId: Int) = AddEditReviewFragment().apply {
            arguments = bundleOf(
                REVIEW_ID to reviewId,
                BEER_ID to beerId
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAddEditReviewBinding.inflate(inflater, container, false)
        binding.addEditReviewViewModel = addEditReviewViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setupListeners(binding)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        reviewId = savedInstanceState?.getInt(REVIEW_ID) ?: arguments?.getInt(REVIEW_ID) ?: NEW_REVIEW_ID
        beerId = savedInstanceState?.getInt(BEER_ID) ?: arguments?.getInt(BEER_ID) ?: DEFAULT_BEER_ID
        this.addEditReviewViewModel = ViewModelProvider(this,
            provideFactory(
                addEditReviewViewModelFactory,
                reviewId,
                beerId
            )
        )[AddEditReviewViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addEditReviewViewModel.reviewLoadingStatus.observe(viewLifecycleOwner) {
            when (it) {
                ReviewLoadingStatus.DONE -> listener?.onModificationsSaved()
                ReviewLoadingStatus.ERROR ->
                    AlertDialog.Builder(requireActivity())
                        .setTitle(R.string.edit_review_error_dialog_title)
                        .setMessage(R.string.edit_review_error_message)
                        .setNeutralButton(android.R.string.ok, null)
                        .show()
                else -> {
                    // Does nothing
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(REVIEW_ID, reviewId)
        outState.putInt(BEER_ID, beerId)
    }

    private fun setupListeners(binding: FragmentAddEditReviewBinding) {
        binding.addEditReviewCancelButton.setOnClickListener {
            listener?.onModificationsCanceled()
        }
        binding.addEditReviewValidateButton.setOnClickListener {
            addEditReviewViewModel.addEditReview()
        }
    }

    interface OnInteractionListener {
        fun onModificationsSaved()
        fun onModificationsCanceled()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("The context $context does not implement OnInteractionListener but it is required.")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}