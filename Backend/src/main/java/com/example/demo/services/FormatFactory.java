package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.Entities.*;
import com.example.demo.Models.*;
import com.example.demo.Repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormatFactory {

    @Autowired
    TypeRepository typeRepository;

    //////////////////////////// User ///////////////////////////////////

    public UserModel getUserModelWithBasicInformation(UserEntity userEntity) {
        UserModel userModel = new UserModel();

        if (userEntity != null) {
            userModel.setId(userEntity.getId());
            userModel.setFirstName(userEntity.getFirstName());
            userModel.setLastName(userEntity.getLastName());
            userModel.setGender(userEntity.getGender());
            userModel.setAge(userEntity.getAge());
            userModel.setEmail(userEntity.getEmail());
            userModel.setMobile(userEntity.getMobile());
            userModel.setCreated_at(userEntity.getCreatedAt());
            userModel.setOnLine(userEntity.isOnLine());
            userModel.setPassword(userEntity.getPassword());
            userModel.setStudyLevel(userEntity.getStudyLevel());
            userModel.setLocation(userEntity.getLocation());
            userModel.setImagePath(userEntity.getImagePath());
            userModel.setAnswerModels(
                    new ArrayList<>()/* answerListEntityToListModel(userEntity.getAnswersEntities()) */);// <<<<<<<<
            userModel.setFriends(null);
            userModel.setGroups(null);
            userModel.setPages(null);
            userModel.setPostModels(new ArrayList<>());
            userModel.setSavedPost(new ArrayList<>());
            userModel.setSharedPost(new ArrayList<>());
        }
        return userModel;

    }

    public UserModel convertUserEntityToUserModel(UserEntity userEntity) {
        UserModel userModel = new UserModel();
        userModel.setId(userEntity.getId());
        userModel.setFirstName(userEntity.getFirstName());
        userModel.setLastName(userEntity.getLastName());
        userModel.setLocation(userEntity.getLocation());
        userModel.setStudyLevel(userEntity.getStudyLevel());
        userModel.setImagePath(userEntity.getImagePath());
        userModel.setGender(userEntity.getGender());
        userModel.setAge(userEntity.getAge());
        userModel.setEmail(userEntity.getEmail());
        userModel.setMobile(userEntity.getMobile());
        userModel.setIp(userEntity.getIp());
        userModel.setCreated_at(userEntity.getCreatedAt());
        userModel.setOnLine(userEntity.isOnLine());
        userModel.setPassword(userEntity.getPassword());
        userModel.setIp(userEntity.getIp());
        if (!userEntity.getFriends().isEmpty())
            userModel.setFriends(fetchFriendsFromUserEntity(userEntity.getFriends()));
        userModel.setAnswerModels(answerListEntityToListModel(userEntity.getAnswersEntities()));// <<<<<<<<
        List<GroupModel> groups = new ArrayList<>();
        if (userEntity.getGroups() != null && !userEntity.getGroups().isEmpty()) {
            for (GroupEntity groupEntity : userEntity.getGroups()) {
                groups.add(convertGroupEntityToGroupModel(groupEntity));
            }
        }
        userModel.setGroups(groups);
        List<PageModel> pages = new ArrayList<>();
        if (userEntity.getPages() != null && !userEntity.getPages().isEmpty()) {

            for (PageEntity pageEntity : userEntity.getPages()) {
                pages.add(convertPageEntityToPageModel(pageEntity));
            }
        }
        userModel.setPages(pages);
        userModel.setPostModels(
                postEntityListToModelList(userEntity.getPostEntity(), false, false, false, false, false)); ////////////////////////////////////////////////////////////////
        userModel.setSavedPost(
                new ArrayList<>()/* postEntityListToModelList(userEntity.getSavedPost(), false, false, false) */); ////////////////////////////////////////////////////////////////
        userModel.setSharedPost(
                new ArrayList<>()/* postEntityListToModelList(userEntity.getSharedPost(), false, false, false) */); ////////////////////////////////////////////////////////////////
        userModel.setChats(new ArrayList<>()/* chatListEntityToListModel(userEntity.getChats(), false) */);
        userModel.setMessages(new ArrayList<>()/* messageEntityListToModleList(userEntity.getMessages(), false) */);
        return userModel;
    }

    public UserEntity convertUserModelToUserEntity(UserModel userModel, boolean useIdFromModel) {
        UserEntity userEntity = new UserEntity();
        if (userModel == null)
            return null;
        if (useIdFromModel)
            userEntity.setId(userModel.getId());
        userEntity.setStudyLevel(userModel.getStudyLevel());
        userEntity.setFirstName(userModel.getFirstName());
        userEntity.setLastName(userModel.getLastName());
        userEntity.setAge(userModel.getAge());
        userEntity.setImagePath(userModel.getImagePath());
        userEntity.setLocation(userModel.getLocation());
        userEntity.setCreatedAt(userModel.getCreated_at());
        userEntity.setEmail(userModel.getEmail());
        userEntity.setGender(userModel.getGender());
        userEntity.setMobile(userModel.getMobile());
        userEntity.setOnLine(userModel.isOnLine());
        userEntity.setPassword(userModel.getPassword());
        userEntity.setFriends(new ArrayList<>());
        userEntity.setPassword(userModel.getPassword());
        userEntity.setIp(userModel.getIp());
        userEntity.setPostEntity(postModelListToEntityList(userModel.getPostModels())); /////////////////////////////////////////////////
        userEntity.setSavedPost(postModelListToEntityList(userModel.getSavedPost())); /////////////////////////////////////////////////
        userEntity.setSharedPost(postModelListToEntityList(userModel.getSharedPost())); /////////////////////////////////////////////////
        userEntity.setAnswersEntities(answerListModelToListEntity(userModel.getAnswerModels()));// <<<<<<<<
        return userEntity;
    }

    public List<UserModel> fetchFriendsFromUserEntity(List<UserEntity> friends) {
        List<UserModel> friendsModel = new ArrayList<>();
        UserModel userModel;
        if (friends == null)
            return null;
        for (UserEntity friendEntity : friends) {
            userModel = new UserModel();

            userModel.setId(friendEntity.getId());
            userModel.setFirstName(friendEntity.getFirstName());
            userModel.setLastName(friendEntity.getLastName());
            userModel.setGender(friendEntity.getGender());
            userModel.setAge(friendEntity.getAge());
            userModel.setEmail(friendEntity.getEmail());
            userModel.setIp(friendEntity.getIp());
            userModel.setMobile(friendEntity.getMobile());
            userModel.setCreated_at(friendEntity.getCreatedAt());
            userModel.setOnLine(friendEntity.isOnLine());
            userModel.setFriends(new ArrayList<>());
            userModel.setImagePath(friendEntity.getImagePath());

            friendsModel.add(userModel);
        }
        return friendsModel;
    }

    public List<UserModel> convertUserListEntityToListModel(List<UserEntity> members) {
        List<UserModel> list = new ArrayList<>();
        for (UserEntity userEntity : members) {
            list.add(convertUserEntityToUserModel(userEntity));
        }
        return list;
    }

    public List<UserEntity> convertUserListModelToListEntity(List<UserModel> userModels) {
        List<UserEntity> list = new ArrayList<>();
        if (userModels != null)
            for (UserModel userModel : userModels) {
                list.add(convertUserModelToUserEntity(userModel, true));//////////// true or false ????
            }
        return list;
    }

    //////////////////////////// Group ///////////////////////////////////

    public GroupEntity convertGroupModelToGroupEntity(GroupModel groupModel, UserEntity userEntity,
            boolean userIdFromModel) {
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setCreatedAt(groupModel.getCreatedAt());
        if (userIdFromModel) {
            groupEntity.setId(groupModel.getId());
        }
        groupEntity.setDescreption(groupModel.getDescription());
        groupEntity.setImagePath(groupModel.getImgPath());
        groupEntity.setName(groupModel.getName());
        groupEntity.setMembers(new ArrayList<>());
        groupEntity.setAdmin(userEntity);
        groupEntity.setPostEntities(postModelListToEntityList(groupModel.getPostModels()));///////////////////
        return groupEntity;

    }

    public GroupModel convertGroupEntityToGroupModel(GroupEntity groupEntity) {
        GroupModel groupModel = new GroupModel();
        if (groupEntity == null)
            return null;
        groupModel.setId(groupEntity.getId());
        groupModel.setDescription(groupEntity.getDescreption());
        groupModel.setImgPath(groupEntity.getImagePath());
        groupModel.setCreatedAt(groupEntity.getCreatedAt());
        groupModel.setName(groupEntity.getName());
        groupModel.setMembers(fetchFriendsFromUserEntity(groupEntity.getMembers()));
        groupModel.setAdmin(getUserModelWithBasicInformation(groupEntity.getAdmin()));
        groupModel.setPostModels(new ArrayList<>()/*
                                                   * postEntityListToModelList(groupEntity.getPostEntities(),
                                                   * true,true,true,true,true)
                                                   */);///////////////////////////
        return groupModel;

    }

    public List<GroupModel> convertGroupEntityListToGroupModelList(List<GroupEntity> searchGroup) {
        List<GroupModel> list = new ArrayList<>();
        if (searchGroup != null) {
            for (GroupEntity groupEntity : searchGroup) {
                list.add(convertGroupEntityToGroupModel(groupEntity));
            }

        }
        return list;
    }

    //////////////////////////// Page ///////////////////////////////////

    public PageModel convertPageEntityToPageModel(PageEntity pageEntity) {
        PageModel pageModel = new PageModel();
        if (pageEntity == null)
            return null;
        pageModel.setCreatedAt(pageEntity.getCreatedAt());
        pageModel.setDescription(pageEntity.getDescreption());
        pageModel.setId(pageEntity.getId());
        pageModel.setImgPath(pageEntity.getImgPath());
        pageModel.setName(pageEntity.getName());
        pageModel.setAdmin(getUserModelWithBasicInformation(pageEntity.getAdmin()));
        if (pageEntity.getMembers() != null)
            pageModel.setMembers(fetchFriendsFromUserEntity(pageEntity.getMembers()));
        pageModel.setPostModels(new ArrayList<>()/*
                                                  * postEntityListToModelList(pageEntity.getPostEntities(),
                                                  * false,false,false, false, false)
                                                  */);///////////////////////////////////////
        return pageModel;
    }

    public PageEntity convertPageModelToPageEntity(PageModel pageModel, UserEntity userEntity, boolean useIdFromModel) {
        PageEntity pageEntity = new PageEntity();
        pageEntity.setAdmin(userEntity);
        pageEntity.setName(pageModel.getName());
        pageEntity.setCreatedAt(pageModel.getCreatedAt());
        pageEntity.setMembers(new ArrayList<>());
        pageEntity.setPostEntities(postModelListToEntityList(pageModel.getPostModels()));/////////////////////////////
        if (useIdFromModel) {
            pageEntity.setId(pageModel.getId());
        }
        pageEntity.setDescreption(pageModel.getDescription());
        pageEntity.setImgPath(pageModel.getImgPath());
        return pageEntity;
    }

    public List<PageModel> convertPageEntityListToPageModelList(List<PageEntity> PageModel) {
        List<PageModel> list = new ArrayList<>();
        if (PageModel != null) {
            for (PageEntity pageEntity : PageModel) {
                list.add(convertPageEntityToPageModel(pageEntity));
            }

        }
        return list;
    }

    //////////////////////////// post ///////////////////////////////////

    public PostEntity postModelToEntity(PostModel postModel) {
        // System.out.println("\n\n\n\n\n POST MODEL TO ENTITY \n\n\n\n\n");
        PostEntity postEntity = new PostEntity();
        if (postModel == null)
            return null;
        postEntity.setId(postModel.getId());
        postEntity.setContent(postModel.getContent());
        postEntity.setCreatedAt(postModel.getCreatedAt());
        postEntity.setCommentEntities(commentModelListToEntityList(postModel.getCommentModels()));
        postEntity.setLikeEntities(likeModelListToEntityList(postModel.getLikeModels()));
        // postEntity.setMedia(me);
        postEntity.setParticipants(convertUserListModelToListEntity(postModel.getParticipants())); /// for count of
                                                                                                   /// shares operation
        postEntity.setType(typeRepository.findByType(postModel.getType().getTypename()).get());

        // postEntity.setUserEntity(convertUserModelToUserEntity(
        // postModel.getUserModel(),true)); ////////// true or false here ???!
        // postEntity.setGroupEntity(convertGroupModelToGroupEntity(
        // postModel.getGroupModel(),null,true));
        // postEntity.setPageEntity(convertPageModelToPageEntity(
        // postModel.getPageModel(),null,true));

        return postEntity;
    }

    public PostModel postEntityToModel(PostEntity postEntity, boolean withParticipants, boolean withSavers,
            boolean withGroups, boolean withPages, boolean withLikes) {
        PostModel postModel = new PostModel();
        postModel.setId(postEntity.getId());
        postModel.setContent(postEntity.getContent());
        postModel.setCreatedAt(postEntity.getCreatedAt());
        postModel.setUserModel(getUserModelWithBasicInformation(postEntity.getUserEntity()));
        postModel.setType(typeEntityToModel(postEntity.getType()));
        postModel.setCommentModels(commentEntityListToModelList(postEntity.getCommentEntities()));

        if (postEntity.getMedia() != null && postEntity.getMedia().size() != 0) {
            postModel.setMedia(new ArrayList<>());
            for (MediaEntity media : postEntity.getMedia()) {
                postModel.getMedia().add(convertMediaEntityToMediaModel(media));
            }
        }
        if (withLikes)
            postModel.setLikeModels(likeEntityListToModelList(postEntity.getLikeEntities()));
        if (withGroups)
            postModel.setGroupModel(convertGroupEntityToGroupModel(postEntity.getGroupEntity()));
        if (withPages)
            postModel.setPageModel(convertPageEntityToPageModel(postEntity.getPageEntity()));
        if (withParticipants)
            postModel.setParticipants(convertUserListEntityToListModel(postEntity.getParticipants()));
        if (withSavers)
            postModel.setSavers(convertUserListEntityToListModel(postEntity.getSavers()));
        return postModel;
    }

    public List<PostModel> postEntityListToModelList(List<PostEntity> findAll, boolean withParticipants,
            boolean withSavers, boolean withGroups, boolean withPages, boolean withLikes) {

        List<PostModel> list = new ArrayList<>();
        if (findAll == null)
            return null;
        for (PostEntity postEntity : findAll) {
            list.add(postEntityToModel(postEntity, withParticipants, withSavers, withGroups, withPages, withLikes));
        }

        return list;
    }

    public List<PostModel> postEntityIterableToModelList(Iterable<PostEntity> all, boolean withGroups,
            boolean withPages) {
        List<PostModel> list = new ArrayList<>();
        if (all == null)
            return null;
        for (PostEntity postEntity : all) {
            list.add(postEntityToModel(postEntity, true, true, withGroups, withPages, true));
        }

        return list;
    }

    public List<PostEntity> postModelListToEntityList(List<PostModel> postModelList) {

        List<PostEntity> list = new ArrayList<>();
        if (postModelList != null)
            for (PostModel postModel : postModelList) {
                list.add(postModelToEntity(postModel));
            }

        return list;
    }

    //////////////////////////// comment ///////////////////////////////////

    public CommentEntity commentModelToEntity(CommentModel commentModel) {

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setId(commentModel.getId());
        commentEntity.setContent(commentModel.getContent());
        commentEntity.setImage_path(commentModel.getImagePath());
        commentEntity.setCreatedAt(commentModel.getCreatedAt());
        commentEntity.setPostEntity(postModelToEntity(commentModel.getPostModel()));
        commentEntity.setUserEntity(convertUserModelToUserEntity(commentModel.getUserModel(), true)); /////////////////////////////////////// true
                                                                                                      /////////////////////////////////////// or
                                                                                                      /////////////////////////////////////// false
                                                                                                      /////////////////////////////////////// ???
        return commentEntity;

    }

    public CommentModel commentEntityToModel(CommentEntity commentEntity, boolean withPost) {

        CommentModel commentModel = new CommentModel();
        commentModel.setId(commentEntity.getId());
        commentModel.setContent(commentEntity.getContent());
        commentModel.setImagePath(commentEntity.getImage_path());
        commentModel.setCreatedAt(commentEntity.getCreatedAt());
        if (withPost)
            commentModel
                    .setPostModel(postEntityToModel(commentEntity.getPostEntity(), false, false, false, false, false));
        commentModel.setUserModel(getUserModelWithBasicInformation(commentEntity.getUserEntity()));
        return commentModel;

    }

    public List<CommentModel> commentEntityListToModelList(List<CommentEntity> allByPostId) {
        List<CommentModel> list = new ArrayList<>();
        for (CommentEntity commentEntity : allByPostId) {
            list.add(commentEntityToModel(commentEntity, false));
        }
        return list;
    }

    public List<CommentEntity> commentModelListToEntityList(List<CommentModel> commentModels) {
        List<CommentEntity> list = new ArrayList<>();
        if (commentModels != null)
            for (CommentModel commentModel : commentModels) {
                list.add(commentModelToEntity(commentModel));
            }
        return list;
    }

    //////////////////////////// question ///////////////////////////////////

    public QuestionEntity questionModelToEntity(QuestionModel questionModel) {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setId(questionModel.getId());
        questionEntity.setQuestion(questionModel.getQuestion());
        return questionEntity;
    }

    public QuestionModel questionEntityToModel(QuestionEntity questionEntity) {
        QuestionModel questionModel = new QuestionModel();
        questionModel.setId(questionEntity.getId());
        questionModel.setQuestion(questionEntity.getQuestion());
        return questionModel;
    }

    public List<QuestionEntity> questionListModelToListEntity(List<QuestionModel> questionModels) {
        List<QuestionEntity> list = new ArrayList<QuestionEntity>();
        if (questionModels != null)
            for (QuestionModel questionModel : questionModels) {
                list.add(questionModelToEntity(questionModel));
            }
        return list;
    }

    public List<QuestionModel> questionListEntityToListModel(List<QuestionEntity> questionEntities) {
        List<QuestionModel> list = new ArrayList<QuestionModel>();
        if (questionEntities != null)
            for (QuestionEntity questionEntity : questionEntities) {
                list.add(questionEntityToModel(questionEntity));
            }
        return list;
    }

    //////////////////////////// answer ///////////////////////////////////

    public AnswersEntity answersModelToEntity(AnswerModel answerModel) {
        AnswersEntity answersEntity = new AnswersEntity();
        answersEntity.setId(answerModel.getId());
        answersEntity.setQuestion(questionModelToEntity(answerModel.getQuestion()));
        answersEntity.setAnswer(answerModel.getAnswer());
        answersEntity.setUser(convertUserModelToUserEntity(answerModel.getUser(), true));
        return answersEntity;
    }

    public AnswerModel answersEntityToModel(AnswersEntity answersEntity) {
        AnswerModel answerModel = new AnswerModel();
        answerModel.setId(answersEntity.getId());
        answerModel.setQuestion(questionEntityToModel(answersEntity.getQuestion()));
        answerModel.setAnswer(answersEntity.getAnswer());
        // answerModel.setUser(getUserModelWithBasicInformation(answersEntity.getUser()));
        return answerModel;
    }

    public List<AnswersEntity> answerListModelToListEntity(List<AnswerModel> answerModels) {
        List<AnswersEntity> list = new ArrayList<AnswersEntity>();
        if (answerModels != null)
            for (AnswerModel answerModel : answerModels) {
                list.add(answersModelToEntity(answerModel));
            }
        return list;
    }

    public List<AnswerModel> answerListEntityToListModel(List<AnswersEntity> answersEntities) {
        List<AnswerModel> list = new ArrayList<AnswerModel>();
        if (answersEntities != null)
            for (AnswersEntity answersEntity : answersEntities) {
                list.add(answersEntityToModel(answersEntity));
            }
        return list;
    }

    //////////////////////////// reaction ///////////////////////////////////

    public ReactionEntity reactionModelToEntity(ReactionModel reactionModel) {
        ReactionEntity reactionsEntity = new ReactionEntity();
        reactionsEntity.setId(reactionModel.getId());
        reactionsEntity.setReactionType(reactionModel.getReactionType());
        reactionsEntity.setColorName(reactionModel.getColorName());
        return reactionsEntity;
    }

    public ReactionModel reactionEntityToModel(ReactionEntity reactionsEntity) {
        ReactionModel reactionModel = new ReactionModel();
        reactionModel.setId(reactionsEntity.getId());
        reactionModel.setReactionType(reactionsEntity.getReactionType());
        reactionModel.setColorName(reactionsEntity.getColorName());
        return reactionModel;
    }

    public List<ReactionModel> reactionListEntityToModel(Iterable<ReactionEntity> rIterable) {
        List<ReactionModel> reactionModels = new ArrayList<>();
        for (ReactionEntity reactionEntity : rIterable) {
            reactionModels.add(reactionEntityToModel(reactionEntity));
        }
        return reactionModels;
    }

    //////////////////////////// like ///////////////////////////////////

    public LikeEntity likeModleToEntity(LikeModel likeModel) {
        LikeEntity likeEntity = new LikeEntity();
        likeEntity.setId(likeModel.getId());
        likeEntity.setPostEntity(postModelToEntity(likeModel.getPostModel()));
        likeEntity.setUserEntity(convertUserModelToUserEntity(likeModel.getUserModel(), true));
        likeEntity.setReaction(reactionModelToEntity(likeModel.getReactionModel()));
        return likeEntity;
    }

    public LikeModel likeEntityToModel(LikeEntity likeEntity, boolean withPost, boolean withUser) {
        LikeModel likeModel = new LikeModel();
        likeModel.setId(likeEntity.getId());
        likeModel.setReactionModel(reactionEntityToModel(likeEntity.getReaction()));
        if (withPost)
            likeModel.setPostModel(postEntityToModel(likeEntity.getPostEntity(), false, false, false, false, false));
        if (withUser)
            likeModel.setUserModel(convertUserEntityToUserModel(likeEntity.getUserEntity()));
        return likeModel;
    }

    public List<LikeModel> likeEntityListToModelList(List<LikeEntity> likeEntities) {
        List<LikeModel> list = new ArrayList<>();
        for (LikeEntity likeEntity : likeEntities) {
            list.add(likeEntityToModel(likeEntity, false, true));
        }
        return list;
    }

    public List<LikeEntity> likeModelListToEntityList(List<LikeModel> likeModels) {
        List<LikeEntity> list = new ArrayList<>();
        if (likeModels != null)
            for (LikeModel likeModel : likeModels) {
                list.add(likeModleToEntity(likeModel));
            }
        return list;
    }

    //////////////////////////////////////////////////////////////////
    /////////////////////////////// Media ////////////////////////////
    //////////////////////////////////////////////////////////////////

    public MediaEntity convertMediaModelToMediaEntity(MediaModel mediaModel) {
        MediaEntity mediaEntity = new MediaEntity();
        mediaEntity.setId(mediaModel.getId());
        mediaEntity.setPath(mediaModel.getPath());
        mediaEntity.setType(mediaModel.getType());
        // mediaEntity.setPostEntity(postModelToEntity(mediaModel.getPostModel()));
        return mediaEntity;
    }

    public MediaModel convertMediaEntityToMediaModel(MediaEntity mediaEntity) {
        if (mediaEntity == null)
            return null;
        MediaModel mediaModel = new MediaModel();
        mediaModel.setId(mediaEntity.getId());
        mediaModel.setPath(mediaEntity.getPath());
        mediaModel.setType(mediaEntity.getType());
        return mediaModel;
    }

    public List<MediaEntity> mediaModelListToEntityList(List<MediaModel> mediaModels) {
        List<MediaEntity> mediaEntities = new ArrayList<>();
        if (mediaModels != null) {
            for (MediaModel media : mediaModels) {
                mediaEntities.add(convertMediaModelToMediaEntity(media));
            }
        }
        return mediaEntities;
    }

    public List<MediaModel> mediaEntityListToModelList(List<MediaEntity> mediaEntities) {
        List<MediaModel> mediaModels = new ArrayList<>();
        if (mediaEntities != null) {
            for (MediaEntity media : mediaEntities) {
                mediaModels.add(convertMediaEntityToMediaModel(media));
            }
        }
        return mediaModels;
    }
    //////////////////////////////////////////////////////////////////
    /////////////////////////////// Chat ////////////////////////////
    //////////////////////////////////////////////////////////////////

    public ChatEntity chatModelToEntity(ChatModel chatModel) {
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setId(chatModel.getId());
        chatEntity.setTittleGroup(chatModel.getTittleGroup());
        chatEntity.setImageGroup(chatModel.getImageGroup());
        if (chatModel.getUsers() != null) {
            for (int i = 0; i < chatModel.getUsers().size(); i++) {
                chatEntity.getUsers().add(convertUserModelToUserEntity(chatModel.getUsers().get(i), true));
            }
        } else
            chatEntity.setUsers(new ArrayList<>());

        if (chatModel.getMessages() != null) {
            for (int i = 0; i < chatModel.getMessages().size(); i++) {
                chatEntity.getMessages().add(messageModleToEntity(chatModel.getMessages().get(i)));
            }
        } else
            chatEntity.setMessages(new ArrayList<>());
        if (chatModel.getUsersHiddenChats() != null) {
            for (int i = 0; i < chatModel.getUsers().size(); i++) {
                chatEntity.getUsersHiddenChats()
                        .add(convertUserModelToUserEntity(chatModel.getUsersHiddenChats().get(i), true));
            }
        } else
            chatEntity.setUsersHiddenChats(new ArrayList<>());
        return chatEntity;
    }

    public ChatModel chatEntityToModel(ChatEntity chatEntity, boolean withUsers, boolean withMessage) {

        ChatModel chatModel = new ChatModel();
        chatModel.setId(chatEntity.getId());
        chatModel.setTittleGroup(chatEntity.getTittleGroup());
        chatModel.setImageGroup(chatEntity.getImageGroup());
        if (withMessage) {
            // System.out.println("\n__________\n" +
            // messageEntityListToModleList(chatEntity.getMessages(), false) +
            // "\n__________\n");
            chatModel.setMessages(messageEntityListToModleList(chatEntity.getMessages(), false));
        }
        if (withUsers) {
            chatModel.setUsers(convertUserListEntityToListModel(chatEntity.getUsers()));
            chatModel.setUsersHiddenChats(convertUserListEntityToListModel(chatEntity.getUsersHiddenChats()));
        }

        return chatModel;

    }

    public List<ChatModel> chatIterableEntityToListModel(Iterable<ChatEntity> chatEntities) {
        List<ChatModel> chatModels = new ArrayList<>();
        if (chatEntities != null) {
            for (ChatEntity chatEntity : chatEntities) {
                chatModels.add(chatEntityToModel(chatEntity, false, true));
            }
        }
        return chatModels;
    }

    public List<ChatModel> chatListEntityToListModel(List<ChatEntity> chatEntities, boolean withUsers) {
        List<ChatModel> chatModels = new ArrayList<>();
        if (chatEntities != null) {
            for (ChatEntity chatEntity : chatEntities) {
                chatModels.add(chatEntityToModel(chatEntity, withUsers, true));
            }
        }
        return chatModels;
    }

    public List<ChatEntity> chatListModelToListEntity(List<ChatModel> chatModels) {
        List<ChatEntity> chatEntities = new ArrayList<>();
        if (chatModels != null) {
            for (ChatModel chatModel : chatModels) {
                chatEntities.add(chatModelToEntity(chatModel));
            }
        }
        return chatEntities;
    }

    //////////////////////////////////////////////////////////////////
    ///////////////////////////// Message ///////////////////////////
    //////////////////////////////////////////////////////////////////

    public MessageModel messageEntityToModle(MessageEntity messageEntity, boolean withChats) {
        MessageModel messageModel = new MessageModel();
        messageModel.setId(messageEntity.getId());
        messageModel.setContent(messageEntity.getContent());
        messageModel.setDateOfSent(messageEntity.getDateOfSent());
        messageModel.setS_id(messageEntity.getSender().getId());
        if (withChats)
            messageModel.setChatModel(chatEntityToModel(messageEntity.getChatEntity(), false, false));
        messageModel.setSender(getUserModelWithBasicInformation(messageEntity.getSender()));
        return messageModel;

    }

    public MessageEntity messageModleToEntity(MessageModel messageModel) {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setId(messageModel.getId());
        messageEntity.setContent(messageModel.getContent());
        messageEntity.setDateOfSent(messageModel.getDateOfSent());
        if (messageModel.getChatModel() != null)
            messageEntity.setChatEntity(chatModelToEntity(messageModel.getChatModel()));
        messageEntity.setSender(convertUserModelToUserEntity(messageModel.getSender(), false));
        return messageEntity;
    }

    public List<MessageModel> messageEntityListToModleList(List<MessageEntity> messageEntity, boolean withChats) {
        List<MessageModel> list = new ArrayList<>();
        // System.out.println("\nmessageEntityListToModleList\n");
        if (messageEntity != null) {
            for (MessageEntity message : messageEntity) {
                // System.out.println("\nmessage == \n"+message.getContent()+"\n__________\n");
                list.add(messageEntityToModle(message, withChats));
            }
        }
        return list;
    }

    public List<MessageModel> messageEntityIterableToModleList(Iterable<MessageEntity> messageEntity) {
        List<MessageModel> list = new ArrayList<>();
        if (messageEntity != null) {
            for (MessageEntity message : messageEntity) {
                list.add(messageEntityToModle(message, false));
            }
        }
        return list;
    }

    public List<MessageEntity> messageModleListToEntityList(List<MessageModel> messageModel) {
        List<MessageEntity> list = new ArrayList<>();
        if (messageModel != null) {
            for (MessageModel message : messageModel) {
                list.add(messageModleToEntity(message));
            }
        }
        return list;
    }

    //////////////////////////////////////////////////////////////////
    ///////////////////////////// Type ///////////////////////////
    //////////////////////////////////////////////////////////////////

    public TypeModel typeEntityToModel(TypeEntity type) {
        TypeModel typeModel = new TypeModel();
        typeModel.setId(type.getId());
        typeModel.setTypename(type.getType());
        return typeModel;
    }

    public TypeEntity typeModleToEntity(TypeModel typeModel) {
        TypeEntity typeEntity = new TypeEntity();
        typeEntity.setId(typeModel.getId());
        typeEntity.setType(typeModel.getTypename());
        return typeEntity;
    }

    public List<TypeModel> typeListEntityToModel(Iterable<TypeEntity> typeEntities) {
        List<TypeModel> typeModels = new ArrayList<>();
        for (TypeEntity typeEntity : typeEntities) {
            typeModels.add(typeEntityToModel(typeEntity));
        }
        return typeModels;
    }

}
