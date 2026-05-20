package ru.smarkov.demo.configuration.kafka;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import ru.smarkov.demo.domain.course.dto.CourseDto;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class CourseSerializer implements Serializer<CourseDto> {

    @Override
    public byte[] serialize(String s, CourseDto course) {
        try {
            byte[] serializedName;
            int stringSize;
            if (course == null) {
                return null;
            } else {
                if (course.getTitle() != null) {
                    serializedName = course.getTitle().getBytes(StandardCharsets.UTF_8);
                    stringSize = serializedName.length;
                } else {
                    serializedName = new byte[0];
                    stringSize = 0;
                }

                ByteBuffer buffer = ByteBuffer.allocate(36 + 4 + stringSize);
                buffer.put(course.getId().toString().getBytes(StandardCharsets.UTF_8));
                buffer.putInt(stringSize);
                buffer.put(serializedName);

                return buffer.array();
            }
        } catch (Exception e) {
            throw new SerializationException("Error when serializing course to byte[]" + e);
        }
    }
}
