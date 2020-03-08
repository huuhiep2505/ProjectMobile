package hiepnh.se1304_nguyenhuuhiep.alladapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import hiepnh.se1304_nguyenhuuhiep.R;
import hiepnh.se1304_nguyenhuuhiep.dtos.UserDTO;

public class UserAdapter extends BaseAdapter {

    private List<UserDTO> userDTOList;

    @Override
    public int getCount() {
        return userDTOList.size();
    }

    @Override
    public Object getItem(int i) {
        return userDTOList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return userDTOList.indexOf(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            view = inflater.inflate(R.layout.user_item,viewGroup,false);
        }
        UserDTO dto= userDTOList.get(i);
        TextView txtUsername = view.findViewById(R.id.txtUsername);
        TextView txtRole = view.findViewById(R.id.txtRole);
        TextView txtFullname = view.findViewById(R.id.txtFullname);
        txtUsername.setText(dto.getUsername());
        txtRole.setText(dto.getRole());
        txtFullname.setText(dto.getFullname());
        return view;
    }

    public void setUserDTOList(List<UserDTO> userDTOList) {
        this.userDTOList = userDTOList;
    }
}
