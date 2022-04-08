package com.example.VisualFolderMediaDriveApp.adapter

import com.example.VisualFolderMediaDriveApp.R
import com.example.VisualFolderMediaDriveApp.model.Affirmation

class Datasource {
    fun loadAffirmations(): List<Affirmation> {
        return listOf<Affirmation>(
            Affirmation(R.string.affirmation1, "https://cdn.myanimelist.net/images/anime/1142/112957.jpg"),
            Affirmation(R.string.affirmation2, "https://cdn.myanimelist.net/images/anime/1974/98158.jpg"),
            Affirmation(R.string.affirmation3, "https://cdn.myanimelist.net/images/anime/1274/102213.jpg"),
            Affirmation(R.string.affirmation4, "https://styles.redditmedia.com/t5_2yb9w/styles/communityIcon_lqalsz65mlf51.jpg?width=256&s=1d128b09291e6a56cb9a8c770006e2ed7440daec"),
            Affirmation(R.string.affirmation5, "https://styles.redditmedia.com/t5_2yb9w/styles/communityIcon_lqalsz65mlf51.jpg?width=256&s=1d128b09291e6a56cb9a8c770006e2ed7440daec"),
            Affirmation(R.string.affirmation6, "https://styles.redditmedia.com/t5_2yb9w/styles/communityIcon_lqalsz65mlf51.jpg?width=256&s=1d128b09291e6a56cb9a8c770006e2ed7440daec"),
            Affirmation(R.string.affirmation7, "https://styles.redditmedia.com/t5_2yb9w/styles/communityIcon_lqalsz65mlf51.jpg?width=256&s=1d128b09291e6a56cb9a8c770006e2ed7440daec"),
            Affirmation(R.string.affirmation8, "https://styles.redditmedia.com/t5_2yb9w/styles/communityIcon_lqalsz65mlf51.jpg?width=256&s=1d128b09291e6a56cb9a8c770006e2ed7440daec"),
            Affirmation(R.string.affirmation9, "https://styles.redditmedia.com/t5_2yb9w/styles/communityIcon_lqalsz65mlf51.jpg?width=256&s=1d128b09291e6a56cb9a8c770006e2ed7440daec"),
            Affirmation(R.string.affirmation10, "https://styles.redditmedia.com/t5_2yb9w/styles/communityIcon_lqalsz65mlf51.jpg?width=256&s=1d128b09291e6a56cb9a8c770006e2ed7440daec")
        )
    }
}