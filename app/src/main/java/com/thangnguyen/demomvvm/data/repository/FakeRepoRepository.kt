package com.thangnguyen.demomvvm.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.squareup.moshi.Moshi
import com.thangnguyen.demomvvm.data.Error
import com.thangnguyen.demomvvm.data.Resource
import com.thangnguyen.demomvvm.data.db.entity.Repo
import com.thangnguyen.demomvvm.data.paging.FakeRepoPagingSource
import com.thangnguyen.demomvvm.data.remote.GithubApi
import com.thangnguyen.demomvvm.data.remote.model.RepoResponse
import com.thangnguyen.demomvvm.util.DateJsonAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*

class FakeRepoRepository constructor(private val api: GithubApi) : RepoRepository {

    companion object {
        private const val PAGE_SIZE = 10
        private const val PREFETCH_DISTANCE = 2
    }

    override fun getRepoList(userName: String): Flow<PagingData<Repo>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = PREFETCH_DISTANCE),
            pagingSourceFactory = { FakeRepoPagingSource(userName, api) }
        ).flow
    }

    override fun getRepoDetail(
        userId: String,
        projectId: String
    ): Flow<Resource<Repo>> = flow {
        emit(Resource.Loading)
        val response = fakeApi(fakeRepoData())
        val resource = if (response != null) {
            Resource.Success(response.toEntity())
        } else {
            Resource.Fail(Error.NoValue)
        }
        emit(resource)
    }

    private fun fakeApi(fakeResponse: String): RepoResponse? {
        val moshi = Moshi.Builder()
            .add(Date::class.java, DateJsonAdapter()).build()
        val adapter = moshi.adapter(RepoResponse::class.java)
        return adapter.fromJson(fakeResponse)

    }

    private fun fakeRepoData(): String {
        return " {\n" +
                "    \"id\": 1296269,\n" +
                "    \"node_id\": \"MDEwOlJlcG9zaXRvcnkxMjk2MjY5\",\n" +
                "    \"name\": \"Hello-World\",\n" +
                "    \"full_name\": \"octocat/Hello-World\",\n" +
                "    \"owner\": {\n" +
                "      \"login\": \"octocat\",\n" +
                "      \"id\": 1,\n" +
                "      \"node_id\": \"MDQ6VXNlcjE=\",\n" +
                "      \"avatar_url\": \"https://github.com/images/error/octocat_happy.gif\",\n" +
                "      \"gravatar_id\": \"\",\n" +
                "      \"url\": \"https://api.github.com/users/octocat\",\n" +
                "      \"html_url\": \"https://github.com/octocat\",\n" +
                "      \"followers_url\": \"https://api.github.com/users/octocat/followers\",\n" +
                "      \"following_url\": \"https://api.github.com/users/octocat/following{/other_user}\",\n" +
                "      \"gists_url\": \"https://api.github.com/users/octocat/gists{/gist_id}\",\n" +
                "      \"starred_url\": \"https://api.github.com/users/octocat/starred{/owner}{/repo}\",\n" +
                "      \"subscriptions_url\": \"https://api.github.com/users/octocat/subscriptions\",\n" +
                "      \"organizations_url\": \"https://api.github.com/users/octocat/orgs\",\n" +
                "      \"repos_url\": \"https://api.github.com/users/octocat/repos\",\n" +
                "      \"events_url\": \"https://api.github.com/users/octocat/events{/privacy}\",\n" +
                "      \"received_events_url\": \"https://api.github.com/users/octocat/received_events\",\n" +
                "      \"type\": \"User\",\n" +
                "      \"site_admin\": false\n" +
                "    },\n" +
                "    \"private\": false,\n" +
                "    \"html_url\": \"https://github.com/octocat/Hello-World\",\n" +
                "    \"description\": \"This your first repo!\",\n" +
                "    \"fork\": false,\n" +
                "    \"url\": \"https://api.github.com/repos/octocat/Hello-World\",\n" +
                "    \"archive_url\": \"https://api.github.com/repos/octocat/Hello-World/{archive_format}{/ref}\",\n" +
                "    \"assignees_url\": \"https://api.github.com/repos/octocat/Hello-World/assignees{/user}\",\n" +
                "    \"blobs_url\": \"https://api.github.com/repos/octocat/Hello-World/git/blobs{/sha}\",\n" +
                "    \"branches_url\": \"https://api.github.com/repos/octocat/Hello-World/branches{/branch}\",\n" +
                "    \"collaborators_url\": \"https://api.github.com/repos/octocat/Hello-World/collaborators{/collaborator}\",\n" +
                "    \"comments_url\": \"https://api.github.com/repos/octocat/Hello-World/comments{/number}\",\n" +
                "    \"commits_url\": \"https://api.github.com/repos/octocat/Hello-World/commits{/sha}\",\n" +
                "    \"compare_url\": \"https://api.github.com/repos/octocat/Hello-World/compare/{base}...{head}\",\n" +
                "    \"contents_url\": \"https://api.github.com/repos/octocat/Hello-World/contents/{+path}\",\n" +
                "    \"contributors_url\": \"https://api.github.com/repos/octocat/Hello-World/contributors\",\n" +
                "    \"deployments_url\": \"https://api.github.com/repos/octocat/Hello-World/deployments\",\n" +
                "    \"downloads_url\": \"https://api.github.com/repos/octocat/Hello-World/downloads\",\n" +
                "    \"events_url\": \"https://api.github.com/repos/octocat/Hello-World/events\",\n" +
                "    \"forks_url\": \"https://api.github.com/repos/octocat/Hello-World/forks\",\n" +
                "    \"git_commits_url\": \"https://api.github.com/repos/octocat/Hello-World/git/commits{/sha}\",\n" +
                "    \"git_refs_url\": \"https://api.github.com/repos/octocat/Hello-World/git/refs{/sha}\",\n" +
                "    \"git_tags_url\": \"https://api.github.com/repos/octocat/Hello-World/git/tags{/sha}\",\n" +
                "    \"git_url\": \"git:github.com/octocat/Hello-World.git\",\n" +
                "    \"issue_comment_url\": \"https://api.github.com/repos/octocat/Hello-World/issues/comments{/number}\",\n" +
                "    \"issue_events_url\": \"https://api.github.com/repos/octocat/Hello-World/issues/events{/number}\",\n" +
                "    \"issues_url\": \"https://api.github.com/repos/octocat/Hello-World/issues{/number}\",\n" +
                "    \"keys_url\": \"https://api.github.com/repos/octocat/Hello-World/keys{/key_id}\",\n" +
                "    \"labels_url\": \"https://api.github.com/repos/octocat/Hello-World/labels{/name}\",\n" +
                "    \"languages_url\": \"https://api.github.com/repos/octocat/Hello-World/languages\",\n" +
                "    \"merges_url\": \"https://api.github.com/repos/octocat/Hello-World/merges\",\n" +
                "    \"milestones_url\": \"https://api.github.com/repos/octocat/Hello-World/milestones{/number}\",\n" +
                "    \"notifications_url\": \"https://api.github.com/repos/octocat/Hello-World/notifications{?since,all,participating}\",\n" +
                "    \"pulls_url\": \"https://api.github.com/repos/octocat/Hello-World/pulls{/number}\",\n" +
                "    \"releases_url\": \"https://api.github.com/repos/octocat/Hello-World/releases{/id}\",\n" +
                "    \"ssh_url\": \"git@github.com:octocat/Hello-World.git\",\n" +
                "    \"stargazers_url\": \"https://api.github.com/repos/octocat/Hello-World/stargazers\",\n" +
                "    \"statuses_url\": \"https://api.github.com/repos/octocat/Hello-World/statuses/{sha}\",\n" +
                "    \"subscribers_url\": \"https://api.github.com/repos/octocat/Hello-World/subscribers\",\n" +
                "    \"subscription_url\": \"https://api.github.com/repos/octocat/Hello-World/subscription\",\n" +
                "    \"tags_url\": \"https://api.github.com/repos/octocat/Hello-World/tags\",\n" +
                "    \"teams_url\": \"https://api.github.com/repos/octocat/Hello-World/teams\",\n" +
                "    \"trees_url\": \"https://api.github.com/repos/octocat/Hello-World/git/trees{/sha}\",\n" +
                "    \"clone_url\": \"https://github.com/octocat/Hello-World.git\",\n" +
                "    \"mirror_url\": \"git:git.example.com/octocat/Hello-World\",\n" +
                "    \"hooks_url\": \"https://api.github.com/repos/octocat/Hello-World/hooks\",\n" +
                "    \"svn_url\": \"https://svn.github.com/octocat/Hello-World\",\n" +
                "    \"homepage\": \"https://github.com\",\n" +
                "    \"language\": null,\n" +
                "    \"forks_count\": 9,\n" +
                "    \"stargazers_count\": 80,\n" +
                "    \"watchers_count\": 80,\n" +
                "    \"size\": 108,\n" +
                "    \"default_branch\": \"master\",\n" +
                "    \"open_issues_count\": 0,\n" +
                "    \"is_template\": false,\n" +
                "    \"topics\": [\n" +
                "      \"octocat\",\n" +
                "      \"atom\",\n" +
                "      \"electron\",\n" +
                "      \"api\"\n" +
                "    ],\n" +
                "    \"has_issues\": true,\n" +
                "    \"has_projects\": true,\n" +
                "    \"has_wiki\": true,\n" +
                "    \"has_pages\": false,\n" +
                "    \"has_downloads\": true,\n" +
                "    \"archived\": false,\n" +
                "    \"disabled\": false,\n" +
                "    \"visibility\": \"public\",\n" +
                "    \"pushed_at\": \"2011-01-26T19:06:43Z\",\n" +
                "    \"created_at\": \"2011-01-26T19:01:12Z\",\n" +
                "    \"updated_at\": \"2011-01-26T19:14:43Z\",\n" +
                "    \"permissions\": {\n" +
                "      \"admin\": false,\n" +
                "      \"push\": false,\n" +
                "      \"pull\": true\n" +
                "    },\n" +
                "    \"template_repository\": null\n" +
                "  }"
    }
}