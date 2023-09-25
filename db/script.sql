CREATE DATABASE[KRManagement]
GO

USE [KRManagement]
GO
/****** Object:  Table [dbo].[ChiTietDichVu]    Script Date: 9/25/2023 11:29:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietDichVu](
	[maHoaDon] [nvarchar](50) NOT NULL,
	[maDichVu] [nvarchar](50) NOT NULL,
	[soLuong] [int] NOT NULL,
	[donGia] [float] NOT NULL,
 CONSTRAINT [PK_CTDichVu] PRIMARY KEY CLUSTERED 
(
	[maDichVu] ASC,
	[maHoaDon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChiTietHoaDon]    Script Date: 9/25/2023 11:29:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietHoaDon](
	[maHoaDon] [nvarchar](50) NOT NULL,
	[maPhong] [nvarchar](50) NOT NULL,
	[thoiGianSuDung] [int] NULL,
 CONSTRAINT [PK_ChiTietHoaDon] PRIMARY KEY CLUSTERED 
(
	[maHoaDon] ASC,
	[maPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DichVu]    Script Date: 9/25/2023 11:29:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DichVu](
	[maDichVu] [nvarchar](50) NOT NULL,
	[tenDichVu] [nvarchar](50) NOT NULL,
	[maLoaiDichVu] [nvarchar](50) NOT NULL,
	[donViTinh] [nvarchar](50) NULL,
	[soLuongTon] [int] NOT NULL,
	[giaBan] [float] NOT NULL,
 CONSTRAINT [PK__DichVu__80F48B09894B9BEF] PRIMARY KEY CLUSTERED 
(
	[maDichVu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 9/25/2023 11:29:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[maHoaDon] [nvarchar](50) NOT NULL,
	[maNhanVien] [nvarchar](50) NOT NULL,
	[maKhachHang] [nvarchar](50) NOT NULL,
	[maPhong] [nvarchar](50) NOT NULL,
	[ngayGioDat] [datetime] NOT NULL,
	[ngayGioTra] [datetime] NULL,
	[tinhTrangHD] [int] NOT NULL,
	[khuyenMai] [nvarchar](50) NULL,
	[tongTienHD] [float] NOT NULL,
 CONSTRAINT [PK__HoaDon__026B4D9A45063E22] PRIMARY KEY CLUSTERED 
(
	[maHoaDon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 9/25/2023 11:29:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[maKhachHang] [nvarchar](50) NOT NULL,
	[tenKhachHang] [nvarchar](50) NOT NULL,
	[soDienThoai] [nvarchar](50) NULL,
	[CCCD] [nvarchar](50) NOT NULL,
	[gioiTinh] [bit] NULL,
	[ngaySinh] [date] NULL,
 CONSTRAINT [PK__KhachHan__7A3ECFE488B8BD0E] PRIMARY KEY CLUSTERED 
(
	[maKhachHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiDichVu]    Script Date: 9/25/2023 11:29:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiDichVu](
	[maLoaiDichVu] [nvarchar](50) NOT NULL,
	[tenoaiDichVu] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK__LoaiDich__262078110FD3F867] PRIMARY KEY CLUSTERED 
(
	[maLoaiDichVu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiPhong]    Script Date: 9/25/2023 11:29:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiPhong](
	[maLoaiPhong] [nvarchar](50) NOT NULL,
	[tenLoaiPhong] [nvarchar](50) NOT NULL,
	[sucChua] [int] NOT NULL,
	[giaTien] [float] NOT NULL,
 CONSTRAINT [PK__LoaiPhon__7A3EB71DC0E4EA6D] PRIMARY KEY CLUSTERED 
(
	[maLoaiPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 9/25/2023 11:29:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[maNhanVien] [nvarchar](50) NOT NULL,
	[tenNhanVien] [nvarchar](50) NOT NULL,
	[soDienThoai] [nvarchar](50) NOT NULL,
	[CCCD] [nvarchar](50) NOT NULL,
	[gioiTinh] [bit] NOT NULL,
	[ngaySinh] [date] NOT NULL,
	[diaChi] [nvarchar](50) NOT NULL,
	[chucVu] [nvarchar](50) NOT NULL,
	[trangThai] [nvarchar](50) NOT NULL,
	[taiKhoan] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK__NhanVien__BDDEF20D0A3E9CBD] PRIMARY KEY CLUSTERED 
(
	[maNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhieuDatPhong]    Script Date: 9/25/2023 11:29:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhieuDatPhong](
	[maPhieuDat] [nvarchar](50) NOT NULL,
	[thoiGianDat] [datetime] NULL,
	[thoiGianNhanPhong] [datetime] NULL,
	[maNhanVien] [nvarchar](50) NOT NULL,
	[maKhachHang] [nvarchar](50) NULL,
	[maPhong] [nvarchar](50) NULL,
 CONSTRAINT [PK_PhieuDatPhong] PRIMARY KEY CLUSTERED 
(
	[maPhieuDat] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Phong]    Script Date: 9/25/2023 11:29:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Phong](
	[maPhong] [nvarchar](50) NOT NULL,
	[maLoaiPhong] [nvarchar](50) NOT NULL,
	[tinhTrang] [int] NOT NULL,
	[viTri] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK__Phong__4CD55E10EE047859] PRIMARY KEY CLUSTERED 
(
	[maPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 9/25/2023 11:29:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[taiKhoan] [nvarchar](50) NOT NULL,
	[matKhau] [nvarchar](50) NOT NULL,
	[tinhTrang] [bit] NOT NULL,
 CONSTRAINT [PK_TaiKhoan] PRIMARY KEY CLUSTERED 
(
	[taiKhoan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[TaiKhoan] ([taiKhoan], [matKhau], [tinhTrang]) VALUES (N'nguyendinhduong', N'321', 0)
INSERT [dbo].[TaiKhoan] ([taiKhoan], [matKhau], [tinhTrang]) VALUES (N'nguyenquangduy', N'1234', 0)
INSERT [dbo].[TaiKhoan] ([taiKhoan], [matKhau], [tinhTrang]) VALUES (N'phuonglinh', N'123456', 1)
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__NhanVien__B4C453187BD25935]    Script Date: 9/25/2023 11:29:40 AM ******/
ALTER TABLE [dbo].[NhanVien] ADD  CONSTRAINT [UQ__NhanVien__B4C453187BD25935] UNIQUE NONCLUSTERED 
(
	[taiKhoan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[ChiTietDichVu] ADD  CONSTRAINT [DF__CTDichVu__soLuon__3C69FB99]  DEFAULT ((1)) FOR [soLuong]
GO
ALTER TABLE [dbo].[ChiTietDichVu] ADD  CONSTRAINT [DF__CTDichVu__donGia__3E52440B]  DEFAULT ((0)) FOR [donGia]
GO
ALTER TABLE [dbo].[DichVu] ADD  CONSTRAINT [DF__DichVu__soLuongT__30F848ED]  DEFAULT ((0)) FOR [soLuongTon]
GO
ALTER TABLE [dbo].[DichVu] ADD  CONSTRAINT [DF__DichVu__giaBan__2F10007B]  DEFAULT ((0)) FOR [giaBan]
GO
ALTER TABLE [dbo].[HoaDon] ADD  CONSTRAINT [DF__HoaDon__ngayGioD__4316F928]  DEFAULT (getdate()) FOR [ngayGioDat]
GO
ALTER TABLE [dbo].[HoaDon] ADD  CONSTRAINT [DF__HoaDon__tinhTran__440B1D61]  DEFAULT ((0)) FOR [tinhTrangHD]
GO
ALTER TABLE [dbo].[LoaiPhong] ADD  CONSTRAINT [DF__LoaiPhong__sucCh__34C8D9D1]  DEFAULT ((0)) FOR [sucChua]
GO
ALTER TABLE [dbo].[LoaiPhong] ADD  CONSTRAINT [DF__LoaiPhong__giaTi__36B12243]  DEFAULT ((0)) FOR [giaTien]
GO
ALTER TABLE [dbo].[NhanVien] ADD  CONSTRAINT [DF__NhanVien__chucVu__267ABA7A]  DEFAULT (N'Nhân Viên') FOR [chucVu]
GO
ALTER TABLE [dbo].[NhanVien] ADD  CONSTRAINT [DF__NhanVien__trangT__286302EC]  DEFAULT (N'Đang làm') FOR [trangThai]
GO
ALTER TABLE [dbo].[Phong] ADD  CONSTRAINT [DF__Phong__tinhTrang__3A81B327]  DEFAULT ((0)) FOR [tinhTrang]
GO
ALTER TABLE [dbo].[ChiTietDichVu]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietDichVu_DichVu] FOREIGN KEY([maDichVu])
REFERENCES [dbo].[DichVu] ([maDichVu])
GO
ALTER TABLE [dbo].[ChiTietDichVu] CHECK CONSTRAINT [FK_ChiTietDichVu_DichVu]
GO
ALTER TABLE [dbo].[ChiTietDichVu]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietDichVu_HoaDon1] FOREIGN KEY([maHoaDon])
REFERENCES [dbo].[HoaDon] ([maHoaDon])
GO
ALTER TABLE [dbo].[ChiTietDichVu] CHECK CONSTRAINT [FK_ChiTietDichVu_HoaDon1]
GO
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietHoaDon_HoaDon] FOREIGN KEY([maHoaDon])
REFERENCES [dbo].[HoaDon] ([maHoaDon])
GO
ALTER TABLE [dbo].[ChiTietHoaDon] CHECK CONSTRAINT [FK_ChiTietHoaDon_HoaDon]
GO
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietHoaDon_Phong] FOREIGN KEY([maPhong])
REFERENCES [dbo].[Phong] ([maPhong])
GO
ALTER TABLE [dbo].[ChiTietHoaDon] CHECK CONSTRAINT [FK_ChiTietHoaDon_Phong]
GO
ALTER TABLE [dbo].[DichVu]  WITH CHECK ADD  CONSTRAINT [FK_DichVu_LoaiDichVu] FOREIGN KEY([maLoaiDichVu])
REFERENCES [dbo].[LoaiDichVu] ([maLoaiDichVu])
GO
ALTER TABLE [dbo].[DichVu] CHECK CONSTRAINT [FK_DichVu_LoaiDichVu]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_KhachHang] FOREIGN KEY([maKhachHang])
REFERENCES [dbo].[KhachHang] ([maKhachHang])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_KhachHang]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_NhanVien] FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVien] ([maNhanVien])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_NhanVien]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_Phong] FOREIGN KEY([maPhong])
REFERENCES [dbo].[Phong] ([maPhong])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_Phong]
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD  CONSTRAINT [FK_NhanVien_TaiKhoan] FOREIGN KEY([taiKhoan])
REFERENCES [dbo].[TaiKhoan] ([taiKhoan])
GO
ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [FK_NhanVien_TaiKhoan]
GO
ALTER TABLE [dbo].[PhieuDatPhong]  WITH CHECK ADD  CONSTRAINT [FK_PhieuDatPhong_KhachHang] FOREIGN KEY([maKhachHang])
REFERENCES [dbo].[KhachHang] ([maKhachHang])
GO
ALTER TABLE [dbo].[PhieuDatPhong] CHECK CONSTRAINT [FK_PhieuDatPhong_KhachHang]
GO
ALTER TABLE [dbo].[PhieuDatPhong]  WITH CHECK ADD  CONSTRAINT [FK_PhieuDatPhong_NhanVien] FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVien] ([maNhanVien])
GO
ALTER TABLE [dbo].[PhieuDatPhong] CHECK CONSTRAINT [FK_PhieuDatPhong_NhanVien]
GO
ALTER TABLE [dbo].[PhieuDatPhong]  WITH CHECK ADD  CONSTRAINT [FK_PhieuDatPhong_Phong] FOREIGN KEY([maPhong])
REFERENCES [dbo].[Phong] ([maPhong])
GO
ALTER TABLE [dbo].[PhieuDatPhong] CHECK CONSTRAINT [FK_PhieuDatPhong_Phong]
GO
ALTER TABLE [dbo].[Phong]  WITH CHECK ADD  CONSTRAINT [FK_Phong_LoaiPhong] FOREIGN KEY([maLoaiPhong])
REFERENCES [dbo].[LoaiPhong] ([maLoaiPhong])
GO
ALTER TABLE [dbo].[Phong] CHECK CONSTRAINT [FK_Phong_LoaiPhong]
GO
ALTER TABLE [dbo].[ChiTietDichVu]  WITH CHECK ADD  CONSTRAINT [CK__CTDichVu__donGia__3F466844] CHECK  (([donGia]>=(0)))
GO
ALTER TABLE [dbo].[ChiTietDichVu] CHECK CONSTRAINT [CK__CTDichVu__donGia__3F466844]
GO
ALTER TABLE [dbo].[DichVu]  WITH CHECK ADD  CONSTRAINT [CK__DichVu__giaBan__300424B4] CHECK  (([giaBan]>=(0)))
GO
ALTER TABLE [dbo].[DichVu] CHECK CONSTRAINT [CK__DichVu__giaBan__300424B4]
GO
ALTER TABLE [dbo].[DichVu]  WITH CHECK ADD  CONSTRAINT [CK__DichVu__giaBan__7B5B524B] CHECK  (([giaBan]>=(0)))
GO
ALTER TABLE [dbo].[DichVu] CHECK CONSTRAINT [CK__DichVu__giaBan__7B5B524B]
GO
ALTER TABLE [dbo].[DichVu]  WITH CHECK ADD  CONSTRAINT [CK__DichVu__soLuongT__31EC6D26] CHECK  (([soLuongTon]>=(0)))
GO
ALTER TABLE [dbo].[DichVu] CHECK CONSTRAINT [CK__DichVu__soLuongT__31EC6D26]
GO
ALTER TABLE [dbo].[DichVu]  WITH CHECK ADD  CONSTRAINT [CK__DichVu__soLuongT__7C4F7684] CHECK  (([soLuongTon]>=(0)))
GO
ALTER TABLE [dbo].[DichVu] CHECK CONSTRAINT [CK__DichVu__soLuongT__7C4F7684]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [CK__HoaDon__tongTien__44FF419A] CHECK  (([tongTienHD]>=(0)))
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [CK__HoaDon__tongTien__44FF419A]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [CK__HoaDon__tongTien__7E37BEF6] CHECK  (([tongTienHD]>=(0)))
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [CK__HoaDon__tongTien__7E37BEF6]
GO
ALTER TABLE [dbo].[LoaiPhong]  WITH CHECK ADD  CONSTRAINT [CK__LoaiPhong__giaTi__37A5467C] CHECK  (([giaTien]>=(0)))
GO
ALTER TABLE [dbo].[LoaiPhong] CHECK CONSTRAINT [CK__LoaiPhong__giaTi__37A5467C]
GO
ALTER TABLE [dbo].[LoaiPhong]  WITH CHECK ADD  CONSTRAINT [CK__LoaiPhong__giaTi__7F2BE32F] CHECK  (([giaTien]>=(0)))
GO
ALTER TABLE [dbo].[LoaiPhong] CHECK CONSTRAINT [CK__LoaiPhong__giaTi__7F2BE32F]
GO
ALTER TABLE [dbo].[LoaiPhong]  WITH CHECK ADD  CONSTRAINT [CK__LoaiPhong__sucCh__00200768] CHECK  (([sucChua]>(0)))
GO
ALTER TABLE [dbo].[LoaiPhong] CHECK CONSTRAINT [CK__LoaiPhong__sucCh__00200768]
GO
ALTER TABLE [dbo].[LoaiPhong]  WITH CHECK ADD  CONSTRAINT [CK__LoaiPhong__sucCh__35BCFE0A] CHECK  (([sucChua]>(0)))
GO
ALTER TABLE [dbo].[LoaiPhong] CHECK CONSTRAINT [CK__LoaiPhong__sucCh__35BCFE0A]
GO
