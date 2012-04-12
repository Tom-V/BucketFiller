package net.minecraft.src.ic2.api;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

/**
 * Provides methods to initiate events and synchronize tile entity fields in SMP.
 * 
 * The methods are transparent between singleplayer and multiplayer - if a method is called in
 * singleplayer, the associated callback will be locally executed. The implementation is different
 * between the client and server versions of IC2.
 * 
 * You'll usually want to use the server->client methods defined here to synchronize information
 * which is needed by the clients outside the GUI, such as rendering the block, playing sounds or
 * producing effects. Anything which is only visible inside the GUI should be synchronized through
 * the Container class associated to the GUI in Container.updateProgressBar().
 */
public final class NetworkHelper {
	// server -> client


	/**
	 * Schedule a TileEntity's field to be updated to the clients in range.
	 *
	 * The updater will query the field's value during the next update, updates happen usually
	 * every 2 ticks. If low latency is important use initiateTileEntityEvent instead.
	 *
	 * IC2's network updates have to get triggered every time, it doesn't continuously poll/send
	 * the field value. Just call updateTileEntityField after every change to a field which needs
	 * network synchronization.
	 *
	 * The following field data types are currently supported:
	 *  - boolean
	 *  - byte
	 *  - float
	 *  - int
	 *  - int[]
	 *  - short
	 *  - short[]
	 *  - String
	 *
	 * Once the update has been processed by the client, it'll call onNetworkUpdate on the client-
	 * side TileEntity if it implements INetworkUpdateListener.
	 *
	 * If this method is being executed on the client (i.e. Singleplayer), it'll just call
	 * INetworkUpdateListener.onNetworkUpdate (if implemented by the te).
	 *
	 * @param te TileEntity to update
	 * @param field Name of the field to update
	 */
	public static void updateTileEntityField(TileEntity te, String field) {
		try {
			Class.forName(getPackage() + ".platform.NetworkManager").getMethod("updateTileEntityField", TileEntity.class, String.class).invoke(null, te, field);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Immediately send an event for the specified TileEntity to the clients in range.
	 *
	 * If this method is being executed on the client (i.e. Singleplayer), it'll just call
	 * INetworkTileEntityEventListener.onNetworkEvent (if implemented by the te).
	 *
	 * @param te TileEntity to notify, should implement INetworkTileEntityEventListener
	 * @param event Arbitrary integer to represent the event, choosing the values is up to you
	 * @param limitRange Limit the notification range to (currently) 20 blocks instead of the
	 *        tracking distance if true
	 */
	public static void initiateTileEntityEvent(TileEntity te, int event, boolean limitRange) {
		try {
			Class.forName(getPackage() + ".platform.NetworkManager").getMethod("initiateTileEntityEvent", TileEntity.class, Integer.TYPE, Boolean.TYPE).invoke(null, te, event, limitRange);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Immediately send an event for the specified Item to the clients in range.
	 *
	 * The item should implement INetworkItemEventListener to receive the event.
	 *
	 * If this method is being executed on the client (i.e. Singleplayer), it'll just call
	 * INetworkItemEventListener.onNetworkEvent (if implemented by the item).
	 *
	 * @param player EntityPlayer holding the item
	 * @param itemStack ItemStack containing the item
	 * @param event Arbitrary integer to represent the event, choosing the values is up to you
	 * @param limitRange Limit the notification range to (currently) 20 blocks instead of the
	 *        tracking distance if true
	 */
	public static void initiateItemEvent(EntityPlayer player, ItemStack itemStack, int event, boolean limitRange) {
		try {
			Class.forName(getPackage() + ".platform.NetworkManager").getMethod("initiateItemEvent", EntityPlayer.class, ItemStack.class, Integer.TYPE, Boolean.TYPE).invoke(null, player, itemStack, event, limitRange);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Schedule a block update (re-render) on the clients in range.
	 *
	 * If this method is being executed on the client (i.e. Singleplayer), it'll just trigger the
	 * block update locally.
	 *
	 * @param world World containing the block
	 * @param x The block's x coordinate
	 * @param y The block's y coordinate
	 * @param z The block's z coordinate
	 */
	public static void announceBlockUpdate(World world, int x, int y, int z) {
		try {
			Class.forName(getPackage() + ".platform.NetworkManager").getMethod("announceBlockUpdate", World.class, Integer.TYPE, Integer.TYPE, Integer.TYPE).invoke(null, world, x, y, z);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	// client -> server
	

	/**
	 * Ask the server to send the values of the fields specified.
	 *
	 * See updateTileEntityField for the supported field types.
	 *
	 * The implementation is currently limited to TileEntitys as data providers. The tile entity
	 * has to be fully initialized when executing this method (i.e. valid worldObj+coords).
	 *
	 * This method doesn't do anything if executed on the server.
	 *
	 * @param dataProvider Object implementing the INetworkDataProvider interface
	 */	
	public static void requestInitialData(INetworkDataProvider dataProvider) {
		try {
			Class.forName(getPackage() + ".platform.NetworkManager").getMethod("requestInitialData", INetworkDataProvider.class).invoke(null, dataProvider);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Immediately send an event for the specified TileEntity to the server.
	 *
	 * This method doesn't do anything if executed on the server.
	 *
	 * @param te TileEntity to notify, should implement INetworkClientTileEntityEventListener
	 * @param event Arbitrary integer to represent the event, choosing the values is up to you
	 */
	public static void initiateClientTileEntityEvent(TileEntity te, int event) {
		try {
			Class.forName(getPackage() + ".platform.NetworkManager").getMethod("initiateClientTileEntityEvent", TileEntity.class, Integer.TYPE).invoke(null, te, event);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Immediately send an event for the specified Item to the clients in range.
	 *
	 * The item should implement INetworkItemEventListener to receive the event.
	 *
	 * This method doesn't do anything if executed on the server.
	 *
	 * @param itemStack ItemStack containing the item
	 * @param event Arbitrary integer to represent the event, choosing the values is up to you
	 */
	public static void initiateClientItemEvent(ItemStack itemStack, int event) {
		try {
			Class.forName(getPackage() + ".platform.NetworkManager").getMethod("initiateClientItemEvent", ItemStack.class, Integer.TYPE).invoke(null, itemStack, event);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Get the base IC2 package name, used internally.
	 * 
	 * @return IC2 package name, if unable to be determined defaults to ic2
	 */
	private static String getPackage() {
		Package pkg = NetworkHelper.class.getPackage();
		if (pkg != null) return pkg.getName().substring(0, pkg.getName().lastIndexOf('.'));
		else return "ic2";
	}
}

