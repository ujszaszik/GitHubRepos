package com.withintent.githubrepos.features.repos.list.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.withintent.compose.layout.CenteredColumn
import com.withintent.compose.paging.PagingColumn
import com.withintent.compose.progress.ProgressBar
import com.withintent.compose.resources.Dimens
import com.withintent.githubrepos.features.repos.list.model.RepoItem

@Composable
fun ReposListScreen(
    isLoading: Boolean,
    hasFinishedLoading: Boolean,
    keyword: String,
    itemsList: List<RepoItem>,
    totalCount: Long,
    onItemClicked: (RepoItem) -> Unit,
    onLoadMore: () -> Unit,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
) {

    CenteredColumn {

        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = { onRefresh() },
        ) {

            Column {

                ReposListHeader(
                    keyword = keyword,
                    totalCount = totalCount
                )

                PagingColumn(
                    modifier = Modifier
                        .padding(horizontal = Dimens.paddingHalf)
                        .padding(top = Dimens.paddingDouble),
                    items = itemsList,
                    itemContent = { item, modifier ->
                        ReposListItemScreen(
                            modifier = modifier,
                            keyword = keyword,
                            repoItem = item,
                            onItemClick = { onItemClicked(it) }
                        )
                    },
                    emptyContent = { ReposListEmptyScreen() },
                    loadingContent = { ProgressBar() },
                    currentlyLoading = isLoading,
                    finishedLoading = hasFinishedLoading,
                    onLoadMore = onLoadMore
                )
            }
        }
    }
}