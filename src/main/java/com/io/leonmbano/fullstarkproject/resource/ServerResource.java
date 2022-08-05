package com.io.leonmbano.fullstarkproject.resource;



import com.io.leonmbano.fullstarkproject.domain.Response;
import com.io.leonmbano.fullstarkproject.domain.Server;
import com.io.leonmbano.fullstarkproject.enumaration.Status;
import com.io.leonmbano.fullstarkproject.service.implementation.ServerInterfaceImpl;



import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;



@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {
    private final ServerInterfaceImpl serverInterfaceImpl;

    @GetMapping("/list")
    public ResponseEntity<Response> getServers() {
        Map<Object, Object> map = Stream.of(new Object[][]{
                        {"server", serverInterfaceImpl.list(30)},})
                .collect(Collectors.toMap(data -> (Object) data[0], data -> (Object) data[1]));

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDate.now())
                        .data(map)
                        .message("Serves retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @GetMapping("/ping/{idAddress}")
    public ResponseEntity<Response> pingServers(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverInterfaceImpl.ping(ipAddress);

        Map<Object, Object> map = Stream.of(new Object[][]{
                        {"server", server},})
                .collect(Collectors.toMap(data -> (Object) data[0], data -> (Object) data[1]));

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDate.now())
                        .data(map)
                        .message(server.getStatus() == Status.SERVER_UP ? "ping success" : "ping failed")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @PostMapping("/save")
    public ResponseEntity<Response> saveServers(@RequestBody @Valid Server server) {
        serverInterfaceImpl.creat(server);

        Map<Object, Object> map = Stream.of(new Object[][]{
                        {"server", serverInterfaceImpl.creat(server)},})
                .collect(Collectors.toMap(data -> (Object) data[0],
                        data -> (Object) data[1]));

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDate.now())
                        .data(map)
                        .message("Server created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getServers(@PathVariable("id") Long id) {
        Server server = serverInterfaceImpl.get(id);

        Map<Object, Object> map = Stream.of(new Object[][]{
                        {"server", server},})
                .collect(Collectors.toMap(data -> (Object) data[0], data -> (Object) data[1]));

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDate.now())
                        .data(map)
                        .message("Server Retrieved ")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteServers(@PathVariable("id") Long id) {
        Map<Object, Object> map = Stream.of(new Object[][]{
                        {"server", serverInterfaceImpl.delete(id)},})
                .collect(Collectors.toMap(data -> (Object) data[0], data -> (Object) data[1]));

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDate.now())
                        .data(map)
                        .message("Server deleted ")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @GetMapping(value = "/image/{fileName}", produces = IMAGE_JPEG_VALUE)
    public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {

        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/Documents/learning projects/springboot/full-stark-project/images/" + fileName));

    }
}