package fu.capstone.outlookintegrationdemo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microsoft.graph.serviceclient.GraphServiceClient;
import com.microsoft.graph.models.Message;
import com.microsoft.graph.models.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GraphService {

    private final GraphServiceClient graphClient;

    // Reads the current logged in user information (Delegated Permission)
    // Docs: https://learn.microsoft.com/en-us/graph/api/user-get?view=graph-rest-1.0&tabs=http
    public User getLoggedInUserInfo() {
        return graphClient
                .me()
                .get();
    }

    // Search for users with the given display name, as a daemon process (Application Permission)
    // Docs: https://learn.microsoft.com/en-us/graph/api/user-list?view=graph-rest-1.0&tabs=http
    public List<User> searchUsersByName(String displayName, int resultCount) {
        return graphClient
                .users()
                .get(request -> {
                    request.queryParameters.top = resultCount;
                    request.queryParameters.search = "\"displayName:%s\"".formatted(displayName);
                })
                .getValue();
    }

    // Read the inbox messages of the current logged in user, including attachments (Delegated Permission)
    // Docs: https://learn.microsoft.com/en-us/graph/api/resources/message?view=graph-rest-1.0
    // Docs: https://learn.microsoft.com/en-us/graph/api/resources/mailfolder?view=graph-rest-1.0
    public List<Message> getLoggedInUserInboxMessagesWithAttachments() {
        return graphClient
                .me()
                .mailFolders()
                .byMailFolderId("inbox")
                .messages()
                .get(request -> {
                    request.queryParameters.top = 10;
                    request.queryParameters.expand = new String[] { "attachments" };
                })
                .getValue();
    }

    // Mark the given message as read (Delegated Permission)
    // Docs: https://learn.microsoft.com/en-us/graph/api/resources/message?view=graph-rest-1.0
    public Message markMessageAsRead(Message message) {
        message.setIsRead(true);
        return graphClient
                .me()
                .messages()
                .byMessageId(message.getId())
                .patch(message);
    }
}
