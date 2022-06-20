package com.yarsi.leprosycare;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    //private  Context context;
    private ArrayList<DataAlarm> rvData;
    private Context context;

    public RecyclerViewAdapter() {
        rvData = new ArrayList<>();
    }

    public RecyclerViewAdapter(ArrayList<DataAlarm> inputData){
        rvData = inputData;
        //this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //membuat view baru
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_item, viewGroup, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    public void remove(DataAlarm item) {
        int position = rvData.indexOf(item);
        if (position > -1) {
            rvData.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public DataAlarm getItem(int position){
        return rvData.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        //viewHolder.tvNamaObat.setBackgroundColor(warnaback);
        //mengambil elemen dari data set pada posisi trtntu
        //mengeset isi view dengan element dari dataset tsbt
        final String JUDUL = rvData.get(i).getJudul();
        final String HARI = rvData.get(i).getHari();
        viewHolder.tvHari.setText(rvData.get(i).getHari()+"");
        viewHolder.tvJam.setText(rvData.get(i).getJam());
        viewHolder.tvMenit.setText(rvData.get(i).getMenit());
        viewHolder.tvjudul.setText(rvData.get(i).getJudul());
        if (rvData.get(i).getHari().equalsIgnoreCase("1") &&
                rvData.get(i).getJudul().substring(0,2).equalsIgnoreCase("PB")){
            viewHolder.tvNamaObat.setText(rvData.get(i).getNamaObat());
            viewHolder.tvNamaObat2.setText(rvData.get(i).getNamaObat2());

        }
        else if(rvData.get(i).getHari().equalsIgnoreCase("1") &&
                rvData.get(i).getJudul().substring(0,2).equalsIgnoreCase("MB")){
            viewHolder.tvNamaObat.setText(rvData.get(i).getNamaObat());
            viewHolder.tvNamaObat2.setText(rvData.get(i).getNamaObat2());
            viewHolder.tvNamaObat3.setText(rvData.get(i).getNamaObat3());
        }else if(!rvData.get(i).getHari().equalsIgnoreCase("1") &&
                rvData.get(i).getJudul().substring(0,2).equalsIgnoreCase("PB")){
            viewHolder.tvNamaObat.setText(rvData.get(i).getNamaObat4());
        }else if(!rvData.get(i).getHari().equalsIgnoreCase("1") &&
                rvData.get(i).getJudul().substring(0,2).equalsIgnoreCase("MB")){
            viewHolder.tvNamaObat.setText(rvData.get(i).getNamaObat4());
            viewHolder.tvNamaObat2.setText(rvData.get(i).getNamaObat5());
        }

        //Mengimplementasikan Menu Popup pada Overflow (ImageButton)
        viewHolder.flow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                //Membuat Instance/Objek dari PopupMenu
                PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                popupMenu.inflate(R.menu.popup_menu);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.hapus:
                                //Menghapus Data Dari Database
                                DBPerawatan getDatabase = new DBPerawatan(view.getContext());
                                SQLiteDatabase DeleteData = getDatabase.getWritableDatabase();
                                //Menentukan di mana bagian kueri yang akan dipilih
                                String selection = DBPerawatan.MyColums.jenis_Perawatan + " LIKE ?";
                                //Menentukan Nama Dari Data Yang Ingin Dihapus
                                String[] selectionArgs = {JUDUL};
                                DeleteData.delete(DBPerawatan.MyColums.namaTabel, selection, selectionArgs);

                                //Menghapus Data pada List dari Posisi Tertentu
                                String position2 = String.valueOf(JUDUL.indexOf(JUDUL));
                                rvData.remove(i);
                                notifyItemRemoved(i);
                                if (position2 == null) {
                                    notifyItemRangeChanged(Integer.parseInt(position2), rvData.size());
                                }
                                break;

                            case R.id.perbarui:
                                Intent dataForm = new Intent(view.getContext(), UpdateHari.class);
                                 dataForm.putExtra("SendHARI", HARI);
                                dataForm.putExtra("sendJUDUL", JUDUL);
                               context.startActivity(dataForm);
                                ((Activity)context).finish();
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        // menghitung ukuran dataset / jumlah data yang ditampilkan di RecyclerView
        return rvData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvHari;
        public TextView tvJam;
        public TextView tvMenit;
        public TextView tvjudul;
        public TextView tvNamaObat;
        public TextView tvNamaObat2;
        public TextView tvNamaObat3;
        public ImageButton flow;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //  title = (LinearLayout) itemView.findViewById(R.id.rv_main);
            tvHari = (TextView) itemView.findViewById(R.id.tv_hari);
            tvJam = (TextView) itemView.findViewById(R.id.tv_jam);
            tvMenit = (TextView) itemView.findViewById(R.id.tv_menit);
            tvjudul = (TextView) itemView.findViewById(R.id.judul_perawatan);
            tvNamaObat = (TextView) itemView.findViewById(R.id.namaObat);
            tvNamaObat2 = (TextView) itemView.findViewById(R.id.namaObat2);
            tvNamaObat3 = (TextView) itemView.findViewById(R.id.namaObat3);
            flow = (ImageButton) itemView.findViewById(R.id.flow);

            context = (Context) itemView.getContext();

        }
    }
}