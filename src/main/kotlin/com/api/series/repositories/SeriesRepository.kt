package com.api.series.repositories

import com.api.series.models.Series
import org.springframework.data.repository.CrudRepository

interface SeriesRepository : CrudRepository<Series,Long>