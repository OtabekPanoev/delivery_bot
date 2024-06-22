package uz.pdp.repository;

import lombok.Getter;
import lombok.NonNull;
import uz.pdp.model.User;
import uz.pdp.model.enams.Lang;
import uz.pdp.model.enams.Role;
import uz.pdp.model.enams.UserState;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import static uz.pdp.utils.DBUtils.getConnection;

public class UserRepository implements BaseRepository<User, Long> {
    @Getter
    private static final UserRepository instance = new UserRepository();

    private UserRepository() {
    }

    @Override
    public Boolean save(User user) {

        try {
            Connection cn = getConnection();

            String sql = "insert into auth_user (chat_id, username, fio, phone_number, role, lang, user_state) values (?,?,?,?,?,?,?);";

            PreparedStatement st = cn.prepareStatement(sql);
            st.setLong(1, user.getChatId());
            st.setString(2, user.getUsername());
            st.setString(3, user.getFio());
            st.setString(4, user.getPhoneNumber());
            st.setString(5, String.valueOf(user.getRole()));
            st.setString(6,String.valueOf(user.getLang()));
            st.setString(7,String.valueOf(user.getUserState()));

            st.executeUpdate();

            cn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    @Override
    public Boolean update(Long id, User oldUser) {
        List<User> users = getAllUsersFromFile();
        for (User user : users) {
            if (user.getChatId().equals(id)) {
                user.setUserState(oldUser.getUserState());
                setAllUsersFromFile(users);
                break;
            }
        }
        return true;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {

        User user = null;
        try {
            Connection cn = getConnection();

            String sql = "SELECT * FROM auth_user WHERE chat_id = ?";

            PreparedStatement st = cn.prepareStatement(sql);
            st.setLong(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {

                long chatId = rs.getLong("chat_id");
                String string = rs.getString("username");
                String fio = rs.getString("fio");
                String phoneNumber = rs.getString("phone_number");
                String role = rs.getString("role");
                String lang = rs.getString("lang");
                String userState = rs.getString("user_state");

                user = User.builder()
                        .chatId(chatId)
                        .username(string)
                        .fio(fio)
                        .phoneNumber(phoneNumber)
                        .role(Role.valueOf(role))
                        .lang(Lang.valueOf(lang))
                        .userState(UserState.valueOf(userState))
                        .build();
            }


            cn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return Optional.ofNullable(user);
    }

    @NonNull
    private List<User> getAllUsersFromFile() {
        return null;
    }

    private void setAllUsersFromFile(List<User> users) {
        return;
    }
}
