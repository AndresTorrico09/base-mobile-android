package com.example.myapplication.presentation.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.model.product.Product
import com.example.myapplication.databinding.ViewHolderProductBinding

class ProductAdapter(
    private var list : List<Product>,
    private var listener: OnProductListener
): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ViewHolderProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ProductViewHolder(
        private val binding: ViewHolderProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product : Product){
            with(binding) {
//                Picasso.get().load(product.thumbnail).into(thumbnail)
                price.text = "$${product.price}"
                description.text = product.title
                root.setOnClickListener { listener.onProductClicked(product) }
            }
        }
    }

    interface OnProductListener {
        fun onProductClicked(product: Product)
    }
}
