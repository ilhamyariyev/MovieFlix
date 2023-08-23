package com.info.movieflix.data.repository


import com.info.movieflix.common.util.Resource
import com.info.movieflix.data.api.MovieApi
import com.info.movieflix.data.dto.CastingResponseModelItem
import com.info.movieflix.data.dto.DetailsResponseModelItem
import com.info.movieflix.data.dto.NowPlayingResponseModelItem
import com.info.movieflix.data.dto.PopularResponseModelItem
import com.info.movieflix.data.dto.RecommendationResponseModelItem
import com.info.movieflix.data.dto.ReviewsResponseModelItem
import com.info.movieflix.data.dto.TopRatedResponseModelItem
import com.info.movieflix.data.dto.TrailerResponseModelItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class movieRepository @Inject constructor(
    private val api: MovieApi,
) {
    fun getNowPlayingData(): Flow<Resource<NowPlayingResponseModelItem>> = flow {
        emit(Resource.Loading)
        val response = api.getNowPlayingApi()
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it as Exception))
    }


    fun getTopRatedData() : Flow<Resource<TopRatedResponseModelItem>> = flow {
        emit(Resource.Loading)
        val response = api.getTopRatedApi()
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it as Exception))
    }

    fun getPopular() : Flow<Resource<PopularResponseModelItem>> = flow {
        emit(Resource.Loading)
        val response = api.getPopularApi()
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it as Exception))
    }

    fun getDetails(id:Int) : Flow<Resource<DetailsResponseModelItem>> = flow {
        emit(Resource.Loading)
        val response = api.getMovieDetail(id)
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it as Exception))
    }

    fun getCredits(id:Int) : Flow<Resource<CastingResponseModelItem>> = flow {
        emit(Resource.Loading)
        val response = api.getMovieCredits(id)
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it as Exception))
    }

    fun getTrailers(id:Int) : Flow<Resource<TrailerResponseModelItem>> = flow {
        emit(Resource.Loading)
        val response = api.getMovieTrailer(id)
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it as Exception))
    }


    fun getRecommendations(id:Int) : Flow<Resource<RecommendationResponseModelItem>> = flow {
        emit(Resource.Loading)
        val response = api.getMovieRecommendations(id)
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it as Exception))
    }

    fun getReviews(id:Int) : Flow<Resource<ReviewsResponseModelItem>> = flow {
        emit(Resource.Loading)
        val response = api.getMovieReviews(id)
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it as Exception))
    }

    fun getSearchData(query:String) : Flow<Resource<TopRatedResponseModelItem>> = flow {
        emit(Resource.Loading)
        val response = api.getSearch(query)
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it as Exception))
    }

  
}