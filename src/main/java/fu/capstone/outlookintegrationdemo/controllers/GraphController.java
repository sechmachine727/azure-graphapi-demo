package fu.capstone.outlookintegrationdemo.controllers;

import fu.capstone.outlookintegrationdemo.services.GraphService;
import com.microsoft.graph.models.Message;
import com.microsoft.graph.models.User;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/graph")
public class GraphController {

    private final GraphService graphService;

    @GetMapping("/me")
    public User getLoggedInUserInfo() {
        return graphService.getLoggedInUserInfo();
    }

    @GetMapping("/users")
    public List<User> searchUsersByName(@RequestParam String displayName, @RequestParam int resultCount) {
        return graphService.searchUsersByName(displayName, resultCount);
    }

    @GetMapping("/inbox")
    public List<Message> getLoggedInUserInboxMessagesWithAttachments() {
        return graphService.getLoggedInUserInboxMessagesWithAttachments();
    }

    @PostMapping("/messages/read")
    public Message markMessageAsRead(@RequestBody Message message) {
        return graphService.markMessageAsRead(message);
    }
}