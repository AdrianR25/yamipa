package io.josemmo.bukkit.plugin.commands.arguments;

import java.util.concurrent.CompletableFuture;

import org.bukkit.Rotation;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

public class RotationArgument extends StringArgument{
    /**
     * Rotation Argument constructor
     * @param name Argument name
     */
    public RotationArgument(@NotNull String name) {
        super(name);
    }

    @Override
    public @NotNull RequiredArgumentBuilder<?, ?> build() {
        return super.build().suggests(this::getSuggestions);
    }

    @Override
    public @NotNull Object parse(@NotNull CommandSender sender, @NotNull Object rawValue) throws CommandSyntaxException {
        Rotation rotation;
        try {
            rotation = Rotation.valueOf((String) rawValue);
        } catch (IllegalArgumentException e) {
            throw newException("Invalid rotation");
        }
        return rotation;
    }

    private @NotNull CompletableFuture<Suggestions> getSuggestions(
        @NotNull CommandContext<?> ctx,
        @NotNull SuggestionsBuilder builder
    ) {
        for (Rotation rotation : Rotation.values()) {
            builder.suggest(rotation.toString());
        }
        return builder.buildFuture();
    }
    
}
