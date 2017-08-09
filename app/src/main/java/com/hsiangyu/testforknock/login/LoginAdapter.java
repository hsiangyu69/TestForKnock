package com.hsiangyu.testforknock.login;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hsiangyu.testforknock.R;
import com.hsiangyu.testforknock.api.response.Member;
import com.hsiangyu.testforknock.databinding.CellLayoutMemberBinding;

import java.util.List;

/**
 * Created by hsiangyuchen on 08/08/2017.
 */

public class LoginAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Member> dataSource;

    /* ------------------------------ Constructor */

    public LoginAdapter(@NonNull List<Member> dataSource) {
        this.dataSource = dataSource;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                      int viewType) {
        CellLayoutMemberBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.cell_layout_member, parent, false);

        return new LoginViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder,
                                 int position) {
        ((LoginViewHolder) holder).binding.setName(dataSource.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return dataSource == null
                ? 0
                : dataSource.size();
    }

    /* ----------------------------- View Holder */

    private class LoginViewHolder extends RecyclerView.ViewHolder {
        private CellLayoutMemberBinding binding;

        /**
         * @param binding target binding for cell layout member
         */
        public LoginViewHolder(@NonNull CellLayoutMemberBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
