package io.josemmo.bukkit.plugin.commands.arguments;

import java.util.concurrent.CompletableFuture;

import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

public class BlockFaceArgument extends StringArgument{
    /**
     * Block Face Argument constructor
     * @param name Argument name
     */
    public BlockFaceArgument(@NotNull String name) {
        super(name);
    }

    @Override
    public @NotNull RequiredArgumentBuilder<?, ?> build() {
        return super.build().suggests(this::getSuggestions);
    }

    @Override
    public @NotNull Object parse(@NotNull CommandSender sender, @NotNull Object rawValue) throws CommandSyntaxException {
        BlockFace face;
        try {
            face = BlockFace.valueOf((String) rawValue);
        } catch (IllegalArgumentException e) {
            throw newException("Invalid block face name");
        }
        return face;
    }

    private @NotNull CompletableFuture<Suggestions> getSuggestions(
        @NotNull CommandContext<?> ctx,
        @NotNull SuggestionsBuilder builder
    ) {
        for (BlockFace face : BlockFace.values()) {
            builder.suggest(face.toString());
        }
        return builder.buildFuture();
    }
    
}
