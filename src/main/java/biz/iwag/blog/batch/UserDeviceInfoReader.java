package biz.iwag.blog.batch;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class UserDeviceInfoReader implements ItemReader<UserDeviceInfo> {

    Queue<UserDeviceInfo> data;

    public UserDeviceInfoReader() {
        data = new LinkedList<>();
        data.add(new UserDeviceInfo());
        data.add(new UserDeviceInfo());
    }

    @Override
    public UserDeviceInfo read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (data.isEmpty()) return null;
        UserDeviceInfo udi = data.poll();
        return udi;
    }
}
