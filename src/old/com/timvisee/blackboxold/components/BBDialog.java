package com.timvisee.blackboxold.components;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;

import javax.swing.JDialog;

import com.timvisee.blackboxold.util.WindowUtils;

public class BBDialog extends JDialog {

	private static final long serialVersionUID = 7517115251870072734L;

	/**
     * Creates a modeless dialog without a title and without a specified
     * {@code Frame} owner.  A shared, hidden frame will be
     * set as the owner of the dialog.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by {@code JComponent.getDefaultLocale}.
     * <p>
     * NOTE: This constructor does not allow you to create an unowned
     * {@code JDialog}. To create an unowned {@code JDialog}
     * you must use either the {@code JDialog(Window)} or
     * {@code JDialog(Dialog)} constructor with an argument of
     * {@code null}.
     *
     * @throws HeadlessException if {@code GraphicsEnvironment.isHeadless()}
     *     returns {@code true}.
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     */
    public BBDialog() {
        super();

		// Set the frame icon
		WindowUtils.setWindowIcon(this);
    }

    /**
     * Creates a modeless dialog with the specified {@code Frame}
     * as its owner and an empty title. If {@code owner}
     * is {@code null}, a shared, hidden frame will be set as the
     * owner of the dialog.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by {@code JComponent.getDefaultLocale}.
     * <p>
     * NOTE: This constructor does not allow you to create an unowned
     * {@code JDialog}. To create an unowned {@code JDialog}
     * you must use either the {@code JDialog(Window)} or
     * {@code JDialog(Dialog)} constructor with an argument of
     * {@code null}.
     *
     * @param owner the {@code Frame} from which the dialog is displayed
     * @throws HeadlessException if {@code GraphicsEnvironment.isHeadless()}
     *     returns {@code true}.
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     */
    public BBDialog(Frame owner) {
        super(owner);

		// Set the frame icon
		WindowUtils.setWindowIcon(this);
    }

    /**
     * Creates a dialog with an empty title and the specified modality and
     * {@code Frame} as its owner. If {@code owner} is {@code null},
     * a shared, hidden frame will be set as the owner of the dialog.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by {@code JComponent.getDefaultLocale}.
     * <p>
     * NOTE: This constructor does not allow you to create an unowned
     * {@code JDialog}. To create an unowned {@code JDialog}
     * you must use either the {@code JDialog(Window)} or
     * {@code JDialog(Dialog)} constructor with an argument of
     * {@code null}.
     *
     * @param owner the {@code Frame} from which the dialog is displayed
     * @param modal specifies whether dialog blocks user input to other top-level
     *     windows when shown. If {@code true}, the modality type property is set to
     *     {@code DEFAULT_MODALITY_TYPE}, otherwise the dialog is modeless.
     * @throws HeadlessException if {@code GraphicsEnvironment.isHeadless()}
     *     returns {@code true}.
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     */
    public BBDialog(Frame owner, boolean modal) {
        super(owner, modal);

		// Set the frame icon
		WindowUtils.setWindowIcon(this);
    }

    /**
     * Creates a modeless dialog with the specified title and
     * with the specified owner frame.  If {@code owner}
     * is {@code null}, a shared, hidden frame will be set as the
     * owner of the dialog.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by {@code JComponent.getDefaultLocale}.
     * <p>
     * NOTE: This constructor does not allow you to create an unowned
     * {@code JDialog}. To create an unowned {@code JDialog}
     * you must use either the {@code JDialog(Window)} or
     * {@code JDialog(Dialog)} constructor with an argument of
     * {@code null}.
     *
     * @param owner the {@code Frame} from which the dialog is displayed
     * @param title  the {@code String} to display in the dialog's
     *                  title bar
     * @throws HeadlessException if {@code GraphicsEnvironment.isHeadless()}
     *     returns {@code true}.
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     */
    public BBDialog(Frame owner, String title) {
        super(owner, title);

		// Set the frame icon
		WindowUtils.setWindowIcon(this);
    }

    /**
     * Creates a dialog with the specified title, owner {@code Frame}
     * and modality. If {@code owner} is {@code null},
     * a shared, hidden frame will be set as the owner of this dialog.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by {@code JComponent.getDefaultLocale}.
     * <p>
     * NOTE: Any popup components ({@code JComboBox},
     * {@code JPopupMenu}, {@code JMenuBar})
     * created within a modal dialog will be forced to be lightweight.
     * <p>
     * NOTE: This constructor does not allow you to create an unowned
     * {@code JDialog}. To create an unowned {@code JDialog}
     * you must use either the {@code JDialog(Window)} or
     * {@code JDialog(Dialog)} constructor with an argument of
     * {@code null}.
     *
     * @param owner the {@code Frame} from which the dialog is displayed
     * @param title  the {@code String} to display in the dialog's
     *     title bar
     * @param modal specifies whether dialog blocks user input to other top-level
     *     windows when shown. If {@code true}, the modality type property is set to
     *     {@code DEFAULT_MODALITY_TYPE} otherwise the dialog is modeless
     * @throws HeadlessException if {@code GraphicsEnvironment.isHeadless()}
     *     returns {@code true}.
     *
     * @see java.awt.Dialog.ModalityType
     * @see java.awt.Dialog.ModalityType#MODELESS
     * @see java.awt.Dialog#DEFAULT_MODALITY_TYPE
     * @see java.awt.Dialog#setModal
     * @see java.awt.Dialog#setModalityType
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     */
    public BBDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);

		// Set the frame icon
		WindowUtils.setWindowIcon(this);
    }

    /**
     * Creates a dialog with the specified title,
     * owner {@code Frame}, modality and {@code GraphicsConfiguration}.
     * If {@code owner} is {@code null},
     * a shared, hidden frame will be set as the owner of this dialog.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by {@code JComponent.getDefaultLocale}.
     * <p>
     * NOTE: Any popup components ({@code JComboBox},
     * {@code JPopupMenu}, {@code JMenuBar})
     * created within a modal dialog will be forced to be lightweight.
     * <p>
     * NOTE: This constructor does not allow you to create an unowned
     * {@code JDialog}. To create an unowned {@code JDialog}
     * you must use either the {@code JDialog(Window)} or
     * {@code JDialog(Dialog)} constructor with an argument of
     * {@code null}.
     *
     * @param owner the {@code Frame} from which the dialog is displayed
     * @param title  the {@code String} to display in the dialog's
     *     title bar
     * @param modal specifies whether dialog blocks user input to other top-level
     *     windows when shown. If {@code true}, the modality type property is set to
     *     {@code DEFAULT_MODALITY_TYPE}, otherwise the dialog is modeless.
     * @param gc the {@code GraphicsConfiguration} of the target screen device;
     *     if {@code null}, the default system {@code GraphicsConfiguration}
     *     is assumed
     * @throws HeadlessException if {@code GraphicsEnvironment.isHeadless()}
     *     returns {@code true}.
     * @see java.awt.Dialog.ModalityType
     * @see java.awt.Dialog.ModalityType#MODELESS
     * @see java.awt.Dialog#DEFAULT_MODALITY_TYPE
     * @see java.awt.Dialog#setModal
     * @see java.awt.Dialog#setModalityType
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     * @since 1.4
     */
    public BBDialog(Frame owner, String title, boolean modal,
                   GraphicsConfiguration gc) {
        super(owner, title, modal, gc);

		// Set the frame icon
		WindowUtils.setWindowIcon(this);
    }

    /**
     * Creates a modeless dialog with the specified {@code Dialog}
     * as its owner and an empty title.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by {@code JComponent.getDefaultLocale}.
     *
     * @param owner the owner {@code Dialog} from which the dialog is displayed
     *     or {@code null} if this dialog has no owner
     * @throws HeadlessException {@code if GraphicsEnvironment.isHeadless()}
     *     returns {@code true}.
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     */
    public BBDialog(Dialog owner) {
        super(owner);

		// Set the frame icon
		WindowUtils.setWindowIcon(this);
    }

    /**
     * Creates a dialog with an empty title and the specified modality and
     * {@code Dialog} as its owner.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by {@code JComponent.getDefaultLocale}.
     *
     * @param owner the owner {@code Dialog} from which the dialog is displayed
     *     or {@code null} if this dialog has no owner
     * @param modal specifies whether dialog blocks user input to other top-level
     *     windows when shown. If {@code true}, the modality type property is set to
     *     {@code DEFAULT_MODALITY_TYPE}, otherwise the dialog is modeless.
     * @throws HeadlessException if {@code GraphicsEnvironment.isHeadless()}
     *     returns {@code true}.
     * @see java.awt.Dialog.ModalityType
     * @see java.awt.Dialog.ModalityType#MODELESS
     * @see java.awt.Dialog#DEFAULT_MODALITY_TYPE
     * @see java.awt.Dialog#setModal
     * @see java.awt.Dialog#setModalityType
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     */
    public BBDialog(Dialog owner, boolean modal) {
        super(owner, modal);

		// Set the frame icon
		WindowUtils.setWindowIcon(this);
    }

    /**
     * Creates a modeless dialog with the specified title and
     * with the specified owner dialog.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by {@code JComponent.getDefaultLocale}.
     *
     * @param owner the owner {@code Dialog} from which the dialog is displayed
     *     or {@code null} if this dialog has no owner
     * @param title  the {@code String} to display in the dialog's
     *                  title bar
     * @throws HeadlessException if {@code GraphicsEnvironment.isHeadless()}
     *     returns {@code true}.
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     */
    public BBDialog(Dialog owner, String title) {
        super(owner, title);

		// Set the frame icon
		WindowUtils.setWindowIcon(this);
    }

    /**
     * Creates a dialog with the specified title, modality
     * and the specified owner {@code Dialog}.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by {@code JComponent.getDefaultLocale}.
     *
     * @param owner the owner {@code Dialog} from which the dialog is displayed
     *     or {@code null} if this dialog has no owner
     * @param title  the {@code String} to display in the dialog's
     *     title bar
     * @param modal specifies whether dialog blocks user input to other top-level
     *     windows when shown. If {@code true}, the modality type property is set to
     *     {@code DEFAULT_MODALITY_TYPE}, otherwise the dialog is modeless
     * @throws HeadlessException if {@code GraphicsEnvironment.isHeadless()}
     *     returns {@code true}.
     * @see java.awt.Dialog.ModalityType
     * @see java.awt.Dialog.ModalityType#MODELESS
     * @see java.awt.Dialog#DEFAULT_MODALITY_TYPE
     * @see java.awt.Dialog#setModal
     * @see java.awt.Dialog#setModalityType
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     */
    public BBDialog(Dialog owner, String title, boolean modal) {
        super(owner, title, modal);

		// Set the frame icon
		WindowUtils.setWindowIcon(this);
    }

    /**
     * Creates a dialog with the specified title, owner {@code Dialog},
     * modality and {@code GraphicsConfiguration}.
     *
     * <p>
     * NOTE: Any popup components ({@code JComboBox},
     * {@code JPopupMenu}, {@code JMenuBar})
     * created within a modal dialog will be forced to be lightweight.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by {@code JComponent.getDefaultLocale}.
     *
     * @param owner the owner {@code Dialog} from which the dialog is displayed
     *     or {@code null} if this dialog has no owner
     * @param title  the {@code String} to display in the dialog's
     *     title bar
     * @param modal specifies whether dialog blocks user input to other top-level
     *     windows when shown. If {@code true}, the modality type property is set to
     *     {@code DEFAULT_MODALITY_TYPE}, otherwise the dialog is modeless
     * @param gc the {@code GraphicsConfiguration} of the target screen device;
     *     if {@code null}, the default system {@code GraphicsConfiguration}
     *     is assumed
     * @throws HeadlessException if {@code GraphicsEnvironment.isHeadless()}
     *     returns {@code true}.
     * @see java.awt.Dialog.ModalityType
     * @see java.awt.Dialog.ModalityType#MODELESS
     * @see java.awt.Dialog#DEFAULT_MODALITY_TYPE
     * @see java.awt.Dialog#setModal
     * @see java.awt.Dialog#setModalityType
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     * @since 1.4
     */
    public BBDialog(Dialog owner, String title, boolean modal,
                   GraphicsConfiguration gc) {
        super(owner, title, modal, gc);

		// Set the frame icon
		WindowUtils.setWindowIcon(this);
    }

    /**
     * Creates a modeless dialog with the specified {@code Window}
     * as its owner and an empty title.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by {@code JComponent.getDefaultLocale}.
     *
     * @param owner the {@code Window} from which the dialog is displayed or
     *     {@code null} if this dialog has no owner
     *
     * @throws IllegalArgumentException
     *     if the {@code owner} is not an instance of {@link java.awt.Dialog Dialog}
     *     or {@link java.awt.Frame Frame}
     * @throws IllegalArgumentException
     *     if the {@code owner}'s {@code GraphicsConfiguration} is not from a screen device
     * @throws HeadlessException
     *     when {@code GraphicsEnvironment.isHeadless()} returns {@code true}
     *
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     *
     * @since 1.6
     */
    public BBDialog(Window owner) {
        super(owner);

		// Set the frame icon
		WindowUtils.setWindowIcon(this);
    }

    /**
     * Creates a dialog with an empty title and the specified modality and
     * {@code Window} as its owner.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by {@code JComponent.getDefaultLocale}.
     *
     * @param owner the {@code Window} from which the dialog is displayed or
     *     {@code null} if this dialog has no owner
     * @param modalityType specifies whether dialog blocks input to other
     *     windows when shown. {@code null} value and unsupported modality
     *     types are equivalent to {@code MODELESS}
     *
     * @throws IllegalArgumentException
     *     if the {@code owner} is not an instance of {@link java.awt.Dialog Dialog}
     *     or {@link java.awt.Frame Frame}
     * @throws IllegalArgumentException
     *     if the {@code owner}'s {@code GraphicsConfiguration} is not from a screen device
     * @throws HeadlessException
     *     when {@code GraphicsEnvironment.isHeadless()} returns {@code true}
     * @throws SecurityException
     *     if the calling thread does not have permission to create modal dialogs
     *     with the given {@code modalityType}
     *
     * @see java.awt.Dialog.ModalityType
     * @see java.awt.Dialog#setModal
     * @see java.awt.Dialog#setModalityType
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     *
     * @since 1.6
     */
    public BBDialog(Window owner, ModalityType modalityType) {
        super(owner, modalityType);

		// Set the frame icon
		WindowUtils.setWindowIcon(this);
    }

    /**
     * Creates a modeless dialog with the specified title and owner
     * {@code Window}.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by {@code JComponent.getDefaultLocale}.
     *
     * @param owner the {@code Window} from which the dialog is displayed or
     *     {@code null} if this dialog has no owner
     * @param title the {@code String} to display in the dialog's
     *     title bar or {@code null} if the dialog has no title
     *
     * @throws IllegalArgumentException
     *     if the {@code owner} is not an instance of {@link java.awt.Dialog Dialog}
     *     or {@link java.awt.Frame Frame}
     * @throws IllegalArgumentException
     *     if the {@code owner}'s {@code GraphicsConfiguration} is not from a screen device
     * @throws HeadlessException
     *     when {@code GraphicsEnvironment.isHeadless()} returns {@code true}
     *
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     *
     * @since 1.6
     */
    public BBDialog(Window owner, String title) {
        super(owner, title);

		// Set the frame icon
		WindowUtils.setWindowIcon(this);
    }

    /**
     * Creates a dialog with the specified title, owner {@code Window} and
     * modality.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by {@code JComponent.getDefaultLocale}.
     *
     * @param owner the {@code Window} from which the dialog is displayed or
     *     {@code null} if this dialog has no owner
     * @param title the {@code String} to display in the dialog's
     *     title bar or {@code null} if the dialog has no title
     * @param modalityType specifies whether dialog blocks input to other
     *     windows when shown. {@code null} value and unsupported modality
     *     types are equivalent to {@code MODELESS}
     *
     * @throws IllegalArgumentException
     *     if the {@code owner} is not an instance of {@link java.awt.Dialog Dialog}
     *     or {@link java.awt.Frame Frame}
     * @throws IllegalArgumentException
     *     if the {@code owner}'s {@code GraphicsConfiguration} is not from a screen device
     * @throws HeadlessException
     *     when {@code GraphicsEnvironment.isHeadless()} returns {@code true}
     * @throws SecurityException
     *     if the calling thread does not have permission to create modal dialogs
     *     with the given {@code modalityType}
     *
     * @see java.awt.Dialog.ModalityType
     * @see java.awt.Dialog#setModal
     * @see java.awt.Dialog#setModalityType
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     *
     * @since 1.6
     */
    public BBDialog(Window owner, String title, Dialog.ModalityType modalityType) {
        super(owner, title, modalityType);

		// Set the frame icon
		WindowUtils.setWindowIcon(this);
    }

    /**
     * Creates a dialog with the specified title, owner {@code Window},
     * modality and {@code GraphicsConfiguration}.
     * <p>
     * NOTE: Any popup components ({@code JComboBox},
     * {@code JPopupMenu}, {@code JMenuBar})
     * created within a modal dialog will be forced to be lightweight.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by {@code JComponent.getDefaultLocale}.
     *
     * @param owner the {@code Window} from which the dialog is displayed or
     *     {@code null} if this dialog has no owner
     * @param title the {@code String} to display in the dialog's
     *     title bar or {@code null} if the dialog has no title
     * @param modalityType specifies whether dialog blocks input to other
     *     windows when shown. {@code null} value and unsupported modality
     *     types are equivalent to {@code MODELESS}
     * @param gc the {@code GraphicsConfiguration} of the target screen device;
     *     if {@code null}, the default system {@code GraphicsConfiguration}
     *     is assumed
     * @throws IllegalArgumentException
     *     if the {@code owner} is not an instance of {@link java.awt.Dialog Dialog}
     *     or {@link java.awt.Frame Frame}
     * @throws IllegalArgumentException
     *     if the {@code owner}'s {@code GraphicsConfiguration} is not from a screen device
     * @throws HeadlessException
     *     when {@code GraphicsEnvironment.isHeadless()} returns {@code true}
     * @throws SecurityException
     *     if the calling thread does not have permission to create modal dialogs
     *     with the given {@code modalityType}
     *
     * @see java.awt.Dialog.ModalityType
     * @see java.awt.Dialog#setModal
     * @see java.awt.Dialog#setModalityType
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     *
     * @since 1.6
     */
    public BBDialog(Window owner, String title, Dialog.ModalityType modalityType,
                   GraphicsConfiguration gc) {
        super(owner, title, modalityType, gc);

		// Set the frame icon
		WindowUtils.setWindowIcon(this);
    }
}
