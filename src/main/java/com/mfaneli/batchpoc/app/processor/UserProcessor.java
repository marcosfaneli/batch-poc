package com.mfaneli.batchpoc.app.processor;

import com.mfaneli.batchpoc.app.reader.dto.LineContact;
import com.mfaneli.batchpoc.domain.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserProcessor implements ItemProcessor<LineContact, User> {


    @Override
    public User process(LineContact lineContact) throws Exception {
        User user = new User();
        user.setId(lineContact.getId());
        user.setName(lineContact.getName());

        if (lineContact.getContact().contains("@")){
            user.setEmail(lineContact.getContact());
        } else {
            user.setPhone(lineContact.getContact());
        }

        return user;
    }
}
