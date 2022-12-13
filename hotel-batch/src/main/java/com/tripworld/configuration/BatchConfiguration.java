package com.tripworld.configuration;


import com.tripworld.hotels.Hotel;
import com.tripworld.hotels.HotelRepository;
import com.tripworld.listener.JobListener;
import com.tripworld.pojo.RoomPojo;
import com.tripworld.processors.HotelItemProcessor;
import com.tripworld.processors.RoomItemProcessor;
import com.tripworld.rooms.Room;
import com.tripworld.rooms.RoomRepository;
import com.tripworld.utility.BlankLineRecordSeparatorPolicy;
import com.tripworld.utility.Utility;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
@AllArgsConstructor
@EnableBatchProcessing
public class BatchConfiguration {
    private final JobBuilderFactory jbf;
    private final StepBuilderFactory sbf;
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;

    @Bean
    public FlatFileItemReader<Hotel> csvReaderHotel() {
        FlatFileItemReader<Hotel> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("/hotels.csv"));
        reader.setLineMapper(Utility.getLineMapperHotel());
        reader.setRecordSeparatorPolicy(new BlankLineRecordSeparatorPolicy());
        return reader;
    }

    @Bean
    public FlatFileItemReader<RoomPojo> csvReaderRoom() {
        FlatFileItemReader<RoomPojo> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("/rooms.csv"));
        reader.setLineMapper(Utility.getLineMapperRoom());
        reader.setRecordSeparatorPolicy(new BlankLineRecordSeparatorPolicy());
        return reader;
    }

    @Bean
    public JsonItemReader<Hotel>jsonHotelReader() {
        return new JsonItemReaderBuilder<Hotel>()
                .jsonObjectReader(new JacksonJsonObjectReader<>(Hotel.class))
                .resource(new ClassPathResource("hotels.json"))
                .name("hotelJsonItemReader")
                .build();
    }

    @Bean
    public JsonItemReader<RoomPojo> jsonRoomReader() {
        return new JsonItemReaderBuilder<RoomPojo>()
                .jsonObjectReader(new JacksonJsonObjectReader<>(RoomPojo.class))
                .resource(new ClassPathResource("rooms.json"))
                .name("roomsJsonItemReader")
                .build();
    }

    @Bean
    public ItemWriter<Hotel> writerHotel(){
        return hotels -> {
            System.out.println("Saving hotels Records: " +hotels);
            hotelRepository.saveAll(hotels);
        };
    }

    @Bean
    public ItemWriter<Room> writerRoom(){
        return rooms -> {
            System.out.println("Saving rooms Records: " +rooms);
            roomRepository.saveAll(rooms);
        };
    }

    @Bean
    public JobExecutionListener listener() {
        return new JobListener();
    }

    @Bean
    public HotelItemProcessor processor() {
        return new HotelItemProcessor();
    }

    @Bean
    public RoomItemProcessor processorRoom() {
        return new RoomItemProcessor(hotelRepository);
    }

    @Bean
    public Step step1() {
        return sbf.get("step1")
                .<Hotel, Hotel> chunk(1)
                .reader(jsonHotelReader())
                .processor(processor())
                .writer(writerHotel())
                .build();
    }

    @Bean
    public Step step2() {
        return sbf.get("step2")
                .<RoomPojo, Room> chunk(1)
                .reader(jsonRoomReader())
                .processor(processorRoom())
                .writer(writerRoom())
                .build();
    }

    @Bean
    public Job job(JobExecutionListener jobExecutionListener) {
        return jbf.get("job")
                .incrementer(new RunIdIncrementer())
                .listener(jobExecutionListener)
                .start(splitFlow())
                .build()
                .build();
    }

    @Bean
    public Flow splitFlow() {
        return new FlowBuilder<SimpleFlow>("splitFlow")
                .split(taskExecutor())
                .add(flow1(), flow2())
                .build();
    }

    @Bean
    public Flow flow1() {
        return new FlowBuilder<SimpleFlow>("flow1")
                .start(step1())
                .build();
    }

    @Bean
    public Flow flow2() {
        return new FlowBuilder<SimpleFlow>("flow2")
                .start(step2())
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor("spring_batch");
    }









}
