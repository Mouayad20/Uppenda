package com.example.demo.FilesMangment.controllers;

import com.example.demo.Entities.*;
import com.example.demo.FilesMangment.commons.FileResponse;
import com.example.demo.FilesMangment.storage.StorageException;
import com.example.demo.FilesMangment.storage.StorageService;
import com.example.demo.Repositories.*;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/upload")
public class FileController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    PageRepository pageRepository;
    @Autowired
    MediaRepository mediaRepository;
    @Autowired
    UserService userService;

    private StorageService storageService;


    public FileController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping(path = "/employee", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String saveEmployee(@RequestPart(name = "type") String type, @RequestPart(name = "id") String id,
                               @RequestPart(name = "name") String nameFile, @RequestPart(name = "file") MultipartFile file) {

        // String superPath = "C:\\xampp\\htdocs";
        String superPath = ".\\src\\main\\resources\\static";

        String networkPath = "\\uppendaMedia" + "\\" + type + "\\" + nameFile;

        Path serverPath = Paths.get(superPath + networkPath);

        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            if (file.getOriginalFilename().contains("..")) {
                throw new StorageException(
                        "Cannot store file with relative path outside current directory " + file.getOriginalFilename());
            }
            try (InputStream inputStream = file.getInputStream()) {

                Files.copy(inputStream, serverPath, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }

        if (type.equals("users")) {

            Long lID = Long.parseLong(id);

            UserEntity userEntity = new UserEntity();

            userEntity = userRepository.findById(lID).get();

            userEntity.setImagePath(networkPath.toString());

            userRepository.save(userEntity);
        }

        if (type.equals("comments")) {

            Long lID = Long.parseLong(id);

            CommentEntity commentEntity = new CommentEntity();

            commentEntity = commentRepository.findById(lID).get();

            commentEntity.setImage_path(networkPath.toString());

            commentRepository.save(commentEntity);
        }

        if (type.equals("groups")) {

            Long lID = Long.parseLong(id);

            GroupEntity groupEntity = new GroupEntity();

            groupEntity = groupRepository.findById(lID).get();

            groupEntity.setImagePath(networkPath.toString());

            groupRepository.save(groupEntity);
        }

        if (type.equals("pages")) {

            Long lID = Long.parseLong(id);

            PageEntity pageEntity = new PageEntity();

            pageEntity = pageRepository.findById(lID).get();

            pageEntity.setImgPath(networkPath.toString());

            pageRepository.save(pageEntity);
        }
        if (type.equals("posts")) {

            Long pId = Long.parseLong(id);

            MediaEntity mediaEntity = new MediaEntity();

            mediaEntity = mediaRepository.findById(pId).get();

            mediaEntity.setPath(networkPath.toString());

            mediaRepository.save(mediaEntity);

        }

        return "employee/success";
    }

    @PostMapping("/upload-file")
    @ResponseBody
    public FileResponse uploadFile(@RequestPart(name = "file") MultipartFile file) {
        String name = storageService.store(file);
        String uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(name).toUriString();
        return new FileResponse(name, uri, file.getContentType(), file.getSize());
    }

    @PostMapping("/upload-multiple-files")
    @ResponseBody
    public String uploadMultipleFiles(@ModelAttribute Model model) {
        for (int i = 0; i < model.getDoc().size(); i++) {
            uploadFile(model.getDoc().get(i));
        }
        return "helloworld";
    }

    // @GetMapping("/listOfFiles")
    // public String listAllFiles(Model model) {

    // model.addAttribute("files",
    // storageService.loadAll().map(path ->
    // ServletUriComponentsBuilder.fromCurrentContextPath()
    // .path("/download/").path(path.getFileName().toString()).toUriString())
    // .collect(Collectors.toList()));

    // return "listFiles";
    // }

    // @GetMapping("/download/{filename:.+}")
    // @ResponseBody
    // public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {

    // Resource resource = storageService.loadAsResource(filename);

    // return ResponseEntity.ok()
    // .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
    // resource.getFilename() + "\"")
    // .body(resource);
    // }

}