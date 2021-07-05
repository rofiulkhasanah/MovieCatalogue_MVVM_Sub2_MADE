package com.dicoding.moviecatalogue.core.utils

import com.dicoding.moviecatalogue.core.data.source.remote.response.MovieResponse

object DataTMDB {
    fun generateMovies(): ArrayList<com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity> {
        val movie =
            ArrayList<com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity>()
        movie.add(
            com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity(
                399566,
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "2021-03-24",
                "Godzilla vs. Kong",
                8.3
            )
        )

        movie.add(
            com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity(
                791373,
                "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.",
                "/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                "2021-03-18",
                "Zack Snyder's Justice League",
                8.5
            )
        )

        movie.add(
            com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity(
                460465,
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "/8yhtzsbBExY8mUct2GOk4LDDuGH.jpg",
                "2021-04-07",
                "Mortal Kombat",
                7.3
            )
        )

        movie.add(
            com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity(
                615678,
                "In a world where supervillains are commonplace, two estranged childhood best friends reunite after one devises a treatment that gives them powers to protect their city.",
                "/279yOM4OQREL36B3SECnRxoB4MZ.jpg",
                "2021-04-09",
                "Thunder Force",
                5.9
            )
        )

        movie.add(
            com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity(
                412656,
                "Two unlikely companions embark on a perilous adventure through the badlands of an unexplored planet as they try to escape a dangerous and disorienting reality, where all inner thoughts are seen and heard by everyone.",
                "/9kg73Mg8WJKlB9Y2SAJzeDKAnuB.jpg",
                "2021-02-24",
                "Chaos Walking",
                7.4
            )
        )

        movie.add(
            com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity(
                527774,
                "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.",
                "/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
                "2021-03-03",
                "Raya and the Last Dragon",
                8.3
            )
        )

        movie.add(
            com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity(
                664767,
                "After the vicious slaughter of his family by stone-cold mercenary Sub-Zero, Hanzo Hasashi is exiled to the torturous Netherrealm. There, in exchange for his servitude to the sinister Quan Chi, he’s given a chance to avenge his family – and is resurrected as Scorpion, a lost soul bent on revenge. Back on Earthrealm, Lord Raiden gathers a team of elite warriors – Shaolin monk Liu Kang, Special Forces officer Sonya Blade and action star Johnny Cage – an unlikely band of heroes with one chance to save humanity. To do this, they must defeat Shang Tsung’s horde of Outworld gladiators and reign over the Mortal Kombat tournament.",
                "/4VlXER3FImHeFuUjBShFamhIp9M.jpg",
                "2020-04-12",
                "Mortal Kombat Legends: Scorpion's Revenge",
                8.4
            )
        )

        movie.add(
            com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity(
                458576,
                "A portal transports Cpt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home.",
                "/1UCOF11QCw8kcqvce8LKOO6pimh.jpg",
                "2020-12-03",
                "Monster Hunter",
                7.1
            )
        )

        movie.add(
            com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity(
                793723,
                "Transferred home after a traumatizing combat mission, a highly trained French soldier uses her lethal skills to hunt down the man who hurt her sister.",
                "/fFRq98cW9lTo6di2o4lK1qUAWaN.jpg",
                "2021-03-05",
                "Sentinelle",
                6.1
            )
        )

        movie.add(
            com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity(
                644083,
                "A Dicken’s classic brought thrillingly up to date in the teeming heartland of modern London, where a group of street smart young hustlers plan the heist of the century for the ultimate payday.",
                "/29dCusd9PwHrbDqzxNG35WcpZpS.jpg",
                "2021-01-22",
                "Twist",
                7.0

            )
        )
        return movie
    }

    fun generateRemoteDummyMovies(): ArrayList<MovieResponse> {
        val movie = ArrayList<MovieResponse>()
        movie.add(
            MovieResponse(
                399566,
                "Godzilla vs. Kong",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "2021-03-24",
                8.3
            )
        )
        movie.add(
            MovieResponse(
                791373,
                "Zack Snyder's Justice League",
                "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.",
                "/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                "2021-03-18",
                8.5
            )
        )

        movie.add(
            MovieResponse(
                460465,
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "/8yhtzsbBExY8mUct2GOk4LDDuGH.jpg",
                "2021-04-07",
                7.3
            )
        )

        movie.add(
            MovieResponse(
                615678,
                "Thunder Force",
                "In a world where supervillains are commonplace, two estranged childhood best friends reunite after one devises a treatment that gives them powers to protect their city.",
                "/279yOM4OQREL36B3SECnRxoB4MZ.jpg",
                "2021-04-09",
                5.9
            )
        )

        movie.add(
            MovieResponse(
                412656,
                "Chaos Walking",
                "Two unlikely companions embark on a perilous adventure through the badlands of an unexplored planet as they try to escape a dangerous and disorienting reality, where all inner thoughts are seen and heard by everyone.",
                "/9kg73Mg8WJKlB9Y2SAJzeDKAnuB.jpg",
                "2021-02-24",
                7.4
            )
        )

        movie.add(
            MovieResponse(
                527774,
                "Raya and the Last Dragon",
                "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.",
                "/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
                "2021-03-03",
                8.3
            )
        )

        movie.add(
            MovieResponse(
                664767,
                "Mortal Kombat Legends: Scorpion's Revenge",
                "After the vicious slaughter of his family by stone-cold mercenary Sub-Zero, Hanzo Hasashi is exiled to the torturous Netherrealm. There, in exchange for his servitude to the sinister Quan Chi, he’s given a chance to avenge his family – and is resurrected as Scorpion, a lost soul bent on revenge. Back on Earthrealm, Lord Raiden gathers a team of elite warriors – Shaolin monk Liu Kang, Special Forces officer Sonya Blade and action star Johnny Cage – an unlikely band of heroes with one chance to save humanity. To do this, they must defeat Shang Tsung’s horde of Outworld gladiators and reign over the Mortal Kombat tournament.",
                "/4VlXER3FImHeFuUjBShFamhIp9M.jpg",
                "2020-04-12",
                8.4
            )
        )

        movie.add(
            MovieResponse(
                458576,
                "Monster Hunter",
                "A portal transports Cpt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home.",
                "/1UCOF11QCw8kcqvce8LKOO6pimh.jpg",
                "2020-12-03",
                7.1
            )
        )

        movie.add(
            MovieResponse(
                793723,
                "Sentinelle",
                "Transferred home after a traumatizing combat mission, a highly trained French soldier uses her lethal skills to hunt down the man who hurt her sister.",
                "/fFRq98cW9lTo6di2o4lK1qUAWaN.jpg",
                "2021-03-05",
                6.1
            )
        )

        movie.add(
            MovieResponse(
                644083,
                "Twist",
                "A Dicken’s classic brought thrillingly up to date in the teeming heartland of modern London, where a group of street smart young hustlers plan the heist of the century for the ultimate payday.",
                "/29dCusd9PwHrbDqzxNG35WcpZpS.jpg",
                "2021-01-22",
                7.0
            )
        )
        return movie
    }

}
