package com.pichy.pokemon.world;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Location {

    public World world;
    public int x, y;

}
