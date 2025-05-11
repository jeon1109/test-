package com.example.jeon.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "tb_whitelist")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@SequenceGenerator(name = "SEQ_ACCESS_IP_GENERATOR", sequenceName = "SEQ_ACCESS_IP",
        initialValue = 1, allocationSize = 1)
public class WhiteIp {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ACCESS_IP_GENERATOR")
    @Column(name = "rec_key", columnDefinition = "int8 DEFAULT nextval('SEQ_ACCESS_IP'::regclass)")
    private Long recKey;

    @Column(name = "access_ip", length = 20)
    private String accessIp;

    @Column(name = "access_date")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime accessDate;

}
