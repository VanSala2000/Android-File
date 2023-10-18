package com.example.phillipinetouristaide
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter(private var imageInfo: ImageInfo) :
        SliderViewAdapter<SliderAdapter.SliderViewHolder>() {

        var sliderList: ImageInfo = imageInfo

        override fun getCount(): Int {
            return 3
        }
    
        override fun onCreateViewHolder(parent: ViewGroup?): SliderAdapter.SliderViewHolder {
            val inflate: View =
                LayoutInflater.from(parent!!.context).inflate(R.layout.slider_item, null)
            return SliderViewHolder(inflate)
        }

        override fun onBindViewHolder(viewHolder: SliderAdapter.SliderViewHolder?, position: Int) {
            if (viewHolder != null) {
                Glide.with(viewHolder.itemView).load(sliderList.getImageResourceId(position)).fitCenter()
                    .into(viewHolder.imageView)
            }
        }
    
        class SliderViewHolder(itemView: View?) : SliderViewAdapter.ViewHolder(itemView) {

            var imageView: ImageView = itemView!!.findViewById(R.id.myimage)
        }
    }
