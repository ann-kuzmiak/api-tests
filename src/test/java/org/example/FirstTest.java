package org.example;

import org.example.models.List;
import org.example.models.Spaces;
import org.example.models.Teams;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.io.IOException;

public class FirstTest {
    Assertion assertion = new Assertion();
    ClickUpApiClient clickUpApiClient = new ClickUpApiClient();
    public String spaceId;
    public String createdListId ;

    @BeforeClass
    public void prepare() throws IOException {
        Teams teamsBody = clickUpApiClient.teamsService.getAuthorizedTeams().execute().body();
        assertion.assertTrue(teamsBody.teams.get(0).name.equals("Anna K's Workspace"), "Teams name is not Anna K's Workspace");

        Spaces spacesBody = clickUpApiClient.spacesService.getSpaces(teamsBody.teams.get(0).id).execute().body();
        spaceId = spacesBody.spaces.get(0).id;
    }

    @Test
    public void createList() throws IOException {
        List list = new List();
        list.name = "list6";
        List createdList = clickUpApiClient.listsService.createFolderlessList(spaceId, list).execute().body();
        assertion.assertFalse(createdList.id.isBlank(), "List is not created. List name " + list.name );
        createdListId = createdList.id;
    }

    @Test(dependsOnMethods = "createList")
    public void deleteList() throws IOException {
        clickUpApiClient.listsService.deleteList(createdListId).execute();
        List list = clickUpApiClient.listsService.getList(createdListId).execute().body();
        Assert.assertTrue(list.deleted, "List is not deleted");
    }
}
