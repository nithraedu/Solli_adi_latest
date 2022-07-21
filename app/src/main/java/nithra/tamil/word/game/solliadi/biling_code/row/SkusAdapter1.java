/*
 * Copyright 2017 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nithra.tamil.word.game.solliadi.biling_code.row;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.billingclient.api.SkuDetails;

import java.util.List;

import nithra.tamil.word.game.solliadi.R;
import nithra.tamil.word.game.solliadi.billing.BillingProvider;


/**
 * Adapter for a RecyclerView that shows SKU details for the app.
 * <p>
 * Note: It's done fragment-specific logic independent and delegates control back to the
 * specified handler (implemented inside AcquireFragment in this example)
 * </p>
 */
public class SkusAdapter1 extends RecyclerView.Adapter<RowViewHolder>
        implements RowViewHolder.OnButtonClickListener {
    private List<SkuDetails> mListData;
    private BillingProvider mBillingProvider;
    public Context context;

    public SkusAdapter1(Context mcontext, BillingProvider billingProvider) {
        mBillingProvider = billingProvider;
        context = mcontext;
    }

    public void updateData(List<SkuDetails> data) {
        mListData = data;
        notifyDataSetChanged();
    }

    @Override
    public RowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sku_details_row, parent, false);
        return new RowViewHolder(item, this);
    }

    @Override
    public void onBindViewHolder(RowViewHolder holder, int position) {
        SkuDetails data = getData(position);
        if (data != null) {
            holder.headd.setText(data.getType());

            holder.title.setText(data.getTitle());
            holder.description.setText(data.getDescription());
            holder.price.setText(data.getPrice());
            holder.button.setEnabled(true);
        }

    }

    @Override
    public int getItemCount() {
        return mListData == null ? 0 : mListData.size();
    }

    @Override
    public void onButtonClicked(int position) {

        SkuDetails data = getData(position);
       // Toast.makeText(context, "" + data.getSku(), Toast.LENGTH_SHORT).show();
        mBillingProvider.getBillingManager().initiatePurchaseFlow(data);

    }

    private SkuDetails getData(int position) {
        return mListData == null ? null : mListData.get(position);
    }
}

