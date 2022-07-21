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
package nithra.tamil.word.game.solliadi.biling_code;

import androidx.annotation.IntDef;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.ViewGroup;

import com.android.billingclient.api.SkuDetails;

import java.lang.annotation.Retention;
import java.util.List;


import nithra.tamil.word.game.solliadi.biling_code.row.RowViewHolder;
import nithra.tamil.word.game.solliadi.biling_code.row.UiManager;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Adapter for a RecyclerView that shows SKU details for the app.
 * <p>
 * Note: It's done fragment-specific logic independent and delegates control back to the
 * specified handler (implemented inside AcquireFragment in this example)
 * </p>
 */
public class SkusAdapter extends RecyclerView.Adapter<RowViewHolder> implements RowDataProvider {
    /**
     * Types for adapter rows
     */
    @Retention(SOURCE)
    @IntDef({TYPE_HEADER, TYPE_NORMAL})
    public @interface RowTypeDef {
    }

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;

    private UiManager mUiManager;
    private List<SkuDetails> mListData;

    void setUiManager(UiManager uiManager) {
        mUiManager = uiManager;
    }

    void updateData(List<SkuDetails> data) {
        mListData = data;
        notifyDataSetChanged();
    }

    @Override
    public @RowTypeDef
    int getItemViewType(int position) {
        //return mListData == null ? TYPE_HEADER : mListData.get(position).getType();
        Log.e("====printgetType", "" + position);
        return position;
    }

    @Override
    public RowViewHolder onCreateViewHolder(ViewGroup parent, @RowTypeDef int viewType) {
        return mUiManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RowViewHolder holder, int position) {
        mUiManager.onBindViewHolder(getData(position), holder);
    }

    @Override
    public int getItemCount() {
        return mListData == null ? 0 : mListData.size();
    }

    @Override
    public SkuDetails getData(int position) {
        return mListData == null ? null : mListData.get(position);
    }
}

