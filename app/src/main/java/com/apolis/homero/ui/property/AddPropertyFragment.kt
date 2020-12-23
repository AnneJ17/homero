package com.apolis.homero.ui.property

import android.content.Context
import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apolis.homero.R
import com.apolis.homero.data.models.Address
import com.apolis.homero.helpers.Constants
import com.apolis.homero.helpers.d
import kotlinx.android.synthetic.main.fragment_add_property.view.*
import java.io.Serializable

private const val ARG_COUNTRY = "country"
private const val ARG_ADDRESS = "address"
private const val ARG_STATE = "state"
private const val ARG_CITY = "city"

class AddPropertyFragment : Fragment() {
    var propertyAddress: Address? = null
    var listener: OnFragmentInteraction? = null
    private var country: String? = null
    private var address: String? = null
    private var state: String? = null
    private var city: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            country = it.getString(ARG_COUNTRY)
            address = it.getString(ARG_ADDRESS)
            state = it.getString(ARG_STATE)
            city = it.getString(ARG_CITY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_add_property, container, false)
        init(view)
        return view
    }

    private fun init(view: View) {
        view.edit_text_country.setText(country)
        d("ADDRESS***", "$country, $address, $city, $state")
        view.edit_text_address_line.setText(propertyAddress?.address)
        view.edit_text_city.setText(propertyAddress?.city)
        view.edit_text_state.setText(propertyAddress?.state)
    }

    companion object {
        @JvmStatic
        fun newInstance(country: String, address: String, state: String, city: String) =
            AddPropertyFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_COUNTRY, country)
                    putString(ARG_ADDRESS, address)
                    putString(ARG_STATE, state)
                    putString(ARG_CITY, city)

                }
            }
    }

        interface OnFragmentInteraction {
            fun onNextClicked()
        }

        override fun onAttach(context: Context) {
            super.onAttach(context)
            listener = context as PropertyActivity
        }
    }