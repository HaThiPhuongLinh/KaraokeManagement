USE [KRManagement]
GO
/****** Object:  Table [dbo].[ChiTietDichVu]    Script Date: 12/14/2023 9:55:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietDichVu](
	[maHoaDon] [nvarchar](50) NOT NULL,
	[maDichVu] [nvarchar](50) NOT NULL,
	[soLuong] [int] NOT NULL,
	[giaBan] [int] NOT NULL,
 CONSTRAINT [PK_CTDichVu] PRIMARY KEY CLUSTERED 
(
	[maDichVu] ASC,
	[maHoaDon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChiTietHoaDon]    Script Date: 12/14/2023 9:55:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietHoaDon](
	[maHoaDon] [nvarchar](50) NOT NULL,
	[maPhong] [nvarchar](50) NOT NULL,
	[thoiGianSuDung] [nvarchar](50) NULL,
	[giaPhong] [int] NULL,
 CONSTRAINT [PK_ChiTietHoaDon] PRIMARY KEY CLUSTERED 
(
	[maHoaDon] ASC,
	[maPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DichVu]    Script Date: 12/14/2023 9:55:03 PM ******/
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
	[giaBan] [int] NOT NULL,
 CONSTRAINT [PK__DichVu__80F48B09894B9BEF] PRIMARY KEY CLUSTERED 
(
	[maDichVu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 12/14/2023 9:55:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[maHoaDon] [nvarchar](50) NOT NULL,
	[maNhanVien] [nvarchar](50) NOT NULL,
	[maKhachHang] [nvarchar](50) NOT NULL,
	[maPhong] [nvarchar](50) NOT NULL,
	[thoiGianVao] [datetime] NOT NULL,
	[thoiGianRa] [datetime] NULL,
	[tinhTrangHD] [int] NOT NULL,
	[khuyenMai] [nvarchar](50) NULL,
 CONSTRAINT [PK__HoaDon__026B4D9A45063E22] PRIMARY KEY CLUSTERED 
(
	[maHoaDon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 12/14/2023 9:55:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[maKhachHang] [nvarchar](50) NOT NULL,
	[tenKhachHang] [nvarchar](50) NOT NULL,
	[soDienThoai] [nvarchar](50) NULL,
	[CCCD] [nvarchar](50) NULL,
	[gioiTinh] [bit] NOT NULL,
	[ngaySinh] [date] NOT NULL,
 CONSTRAINT [PK__KhachHan__7A3ECFE488B8BD0E] PRIMARY KEY CLUSTERED 
(
	[maKhachHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiDichVu]    Script Date: 12/14/2023 9:55:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiDichVu](
	[maLoaiDichVu] [nvarchar](50) NOT NULL,
	[tenLoaiDichVu] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK__LoaiDich__262078110FD3F867] PRIMARY KEY CLUSTERED 
(
	[maLoaiDichVu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiPhong]    Script Date: 12/14/2023 9:55:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiPhong](
	[maLoaiPhong] [nvarchar](50) NOT NULL,
	[tenLoaiPhong] [nvarchar](50) NOT NULL,
	[sucChua] [int] NULL,
 CONSTRAINT [PK__LoaiPhon__7A3EB71DC0E4EA6D] PRIMARY KEY CLUSTERED 
(
	[maLoaiPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 12/14/2023 9:55:03 PM ******/
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
/****** Object:  Table [dbo].[PhieuDatPhong]    Script Date: 12/14/2023 9:55:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhieuDatPhong](
	[maPhieuDat] [nvarchar](50) NOT NULL,
	[thoiGianGoi] [datetime] NULL,
	[thoiGianDat] [datetime] NULL,
	[maNhanVien] [nvarchar](50) NOT NULL,
	[maKhachHang] [nvarchar](50) NULL,
	[maPhong] [nvarchar](50) NULL,
	[trangThai] [int] NULL,
 CONSTRAINT [PK_PhieuDatPhong] PRIMARY KEY CLUSTERED 
(
	[maPhieuDat] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Phong]    Script Date: 12/14/2023 9:55:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Phong](
	[maPhong] [nvarchar](50) NOT NULL,
	[maLoaiPhong] [nvarchar](50) NOT NULL,
	[tinhTrang] [nvarchar](50) NOT NULL,
	[viTri] [nvarchar](50) NOT NULL,
	[giaPhong] [int] NULL,
 CONSTRAINT [PK__Phong__4CD55E10EE047859] PRIMARY KEY CLUSTERED 
(
	[maPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 12/14/2023 9:55:03 PM ******/
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
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000005', N'DV001', 1, 70000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000006', N'DV001', 2, 70000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000008', N'DV001', 1, 70000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000010', N'DV001', 1, 70000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000014', N'DV001', 2, 70000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000015', N'DV001', 2, 70000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000016', N'DV001', 2, 70000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000019', N'DV001', 1, 70000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000001', N'DV002', 2, 75000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000004', N'DV002', 1, 75000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000006', N'DV002', 2, 75000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000009', N'DV002', 2, 75000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000004', N'DV003', 2, 85000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000010', N'DV003', 1, 85000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000011', N'DV003', 1, 85000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000006', N'DV004', 1, 30000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000002', N'DV005', 2, 60000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000009', N'DV005', 1, 60000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000012', N'DV005', 1, 60000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000014', N'DV005', 1, 60000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000017', N'DV005', 2, 60000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000018', N'DV005', 1, 60000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000004', N'DV006', 1, 65000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000003', N'DV007', 1, 300000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000018', N'DV007', 2, 300000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000013', N'DV008', 1, 400000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000002', N'DV009', 1, 350000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000004', N'DV009', 1, 350000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000002', N'DV010', 1, 300000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000013', N'DV010', 1, 300000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000017', N'DV010', 1, 300000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000001', N'DV011', 5, 18000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000005', N'DV011', 2, 18000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000006', N'DV011', 5, 18000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000011', N'DV011', 3, 18000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000012', N'DV011', 3, 18000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000014', N'DV011', 1, 18000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000016', N'DV011', 2, 18000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000017', N'DV011', 3, 18000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000004', N'DV012', 4, 20000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000001', N'DV014', 5, 16000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000005', N'DV014', 4, 16000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000015', N'DV014', 2, 16000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000013', N'DV015', 3, 18000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000003', N'DV016', 8, 22000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000006', N'DV016', 2, 22000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000011', N'DV016', 2, 22000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000012', N'DV016', 1, 22000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000013', N'DV016', 3, 22000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000015', N'DV016', 5, 22000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000013', N'DV017', 3, 15000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000015', N'DV017', 3, 15000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000018', N'DV017', 2, 15000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000010', N'DV018', 1, 15000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000014', N'DV018', 3, 15000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000005', N'DV019', 3, 15000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000016', N'DV019', 3, 15000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000006', N'DV020', 1, 15000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000015', N'DV020', 2, 15000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000018', N'DV020', 2, 15000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000013', N'DV022', 1, 17000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000002', N'DV023', 5, 15000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000013', N'DV023', 1, 15000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000015', N'DV023', 1, 15000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000015', N'DV025', 1, 15000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000003', N'DV026', 2, 25000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000013', N'DV026', 1, 25000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000014', N'DV026', 1, 25000)
INSERT [dbo].[ChiTietDichVu] ([maHoaDon], [maDichVu], [soLuong], [giaBan]) VALUES (N'HD0000003', N'DV027', 1, 55000)
GO
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maPhong], [thoiGianSuDung], [giaPhong]) VALUES (N'HD0000001', N'P101', N'3 giờ 2 phút', 350000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maPhong], [thoiGianSuDung], [giaPhong]) VALUES (N'HD0000002', N'P102', N'4 giờ 29 phút', 230000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maPhong], [thoiGianSuDung], [giaPhong]) VALUES (N'HD0000003', N'P109', N'8 giờ 33 phút', 230000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maPhong], [thoiGianSuDung], [giaPhong]) VALUES (N'HD0000004', N'P110', N'12 giờ 13 phút', 180000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maPhong], [thoiGianSuDung], [giaPhong]) VALUES (N'HD0000005', N'P111', N'2 giờ 0 phút', 150000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maPhong], [thoiGianSuDung], [giaPhong]) VALUES (N'HD0000006', N'P102', N'1 giờ 26 phút', 230000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maPhong], [thoiGianSuDung], [giaPhong]) VALUES (N'HD0000007', N'P110', N'0 phút', 180000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maPhong], [thoiGianSuDung], [giaPhong]) VALUES (N'HD0000008', N'P111', N'0 phút', 150000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maPhong], [thoiGianSuDung], [giaPhong]) VALUES (N'HD0000009', N'P106', N'5 giờ 44 phút', 150000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maPhong], [thoiGianSuDung], [giaPhong]) VALUES (N'HD0000010', N'P109', N'28 phút', 230000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maPhong], [thoiGianSuDung], [giaPhong]) VALUES (N'HD0000011', N'P101', N'1 giờ 32 phút', 350000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maPhong], [thoiGianSuDung], [giaPhong]) VALUES (N'HD0000012', N'P105', N'2 giờ 1 phút', 150000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maPhong], [thoiGianSuDung], [giaPhong]) VALUES (N'HD0000013', N'P111', N'2 giờ 46 phút', 150000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maPhong], [thoiGianSuDung], [giaPhong]) VALUES (N'HD0000014', N'P101', N'25 phút', 350000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maPhong], [thoiGianSuDung], [giaPhong]) VALUES (N'HD0000015', N'P105', N'2 giờ 22 phút', 150000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maPhong], [thoiGianSuDung], [giaPhong]) VALUES (N'HD0000016', N'P104', N'1 giờ 13 phút', 350000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maPhong], [thoiGianSuDung], [giaPhong]) VALUES (N'HD0000017', N'P111', N'2 giờ 31 phút', 150000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maPhong], [thoiGianSuDung], [giaPhong]) VALUES (N'HD0000018', N'P102', N'23 phút', 230000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maPhong], [thoiGianSuDung], [giaPhong]) VALUES (N'HD0000019', N'P113', N'24 phút', 230000)
GO
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [donViTinh], [soLuongTon], [giaBan]) VALUES (N'DV001', N'Trái cây', N'LDV01', N'Đĩa', 42, 70000)
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [donViTinh], [soLuongTon], [giaBan]) VALUES (N'DV002', N'Khô gà', N'LDV04', N'Đĩa', 43, 75000)
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [donViTinh], [soLuongTon], [giaBan]) VALUES (N'DV003', N'Khô bò', N'LDV04', N'Đĩa', 33, 85000)
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [donViTinh], [soLuongTon], [giaBan]) VALUES (N'DV004', N'Đậu phộng', N'LDV04', N'Đĩa', 46, 30000)
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [donViTinh], [soLuongTon], [giaBan]) VALUES (N'DV005', N'Cơm chiên dương châu', N'LDV05', N'Đĩa', 34, 60000)
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [donViTinh], [soLuongTon], [giaBan]) VALUES (N'DV006', N'Mì xào hải sản', N'LDV05', N'Đĩa', 26, 65000)
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [donViTinh], [soLuongTon], [giaBan]) VALUES (N'DV007', N'Tôm sú hấp bia', N'LDV05', N'Kg', 24, 300000)
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [donViTinh], [soLuongTon], [giaBan]) VALUES (N'DV008', N'Ghẹ hấp bia', N'LDV05', N'Kg', 22, 400000)
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [donViTinh], [soLuongTon], [giaBan]) VALUES (N'DV009', N'Lẩu hải sản', N'LDV05', N'Nồi', 21, 350000)
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [donViTinh], [soLuongTon], [giaBan]) VALUES (N'DV010', N'Gà quay Tứ Xuyên', N'LDV05', N'Kg', 29, 300000)
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [donViTinh], [soLuongTon], [giaBan]) VALUES (N'DV011', N'Bia Tiger', N'LDV02', N'Lon', 112, 18000)
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [donViTinh], [soLuongTon], [giaBan]) VALUES (N'DV012', N'Bia Heineken', N'LDV02', N'Lon', 114, 20000)
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [donViTinh], [soLuongTon], [giaBan]) VALUES (N'DV013', N'Bia 333', N'LDV02', N'Lon', 78, 15000)
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [donViTinh], [soLuongTon], [giaBan]) VALUES (N'DV014', N'Bia Sài Gòn', N'LDV02', N'Lon', 120, 16000)
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [donViTinh], [soLuongTon], [giaBan]) VALUES (N'DV015', N'Bia Tiger', N'LDV02', N'Lon', 135, 18000)
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [donViTinh], [soLuongTon], [giaBan]) VALUES (N'DV016', N'Bia Budweiser', N'LDV02', N'Lon', 139, 22000)
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [donViTinh], [soLuongTon], [giaBan]) VALUES (N'DV017', N'Coca', N'LDV03', N'Lon', 154, 15000)
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [donViTinh], [soLuongTon], [giaBan]) VALUES (N'DV018', N'Pepsi', N'LDV03', N'Lon', 145, 15000)
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [donViTinh], [soLuongTon], [giaBan]) VALUES (N'DV019', N'7 Up', N'LDV03', N'Lon', 162, 15000)
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [donViTinh], [soLuongTon], [giaBan]) VALUES (N'DV020', N'Mirinda xá xị', N'LDV03', N'Lon', 136, 15000)
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [donViTinh], [soLuongTon], [giaBan]) VALUES (N'DV021', N'Sting', N'LDV03', N'Lon', 145, 15000)
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [donViTinh], [soLuongTon], [giaBan]) VALUES (N'DV022', N'Red bull', N'LDV03', N'Lon', 149, 17000)
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [donViTinh], [soLuongTon], [giaBan]) VALUES (N'DV023', N'Trà ô long', N'LDV03', N'Chai', 134, 15000)
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [donViTinh], [soLuongTon], [giaBan]) VALUES (N'DV024', N'C2', N'LDV03', N'Lon', 143, 15000)
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [donViTinh], [soLuongTon], [giaBan]) VALUES (N'DV025', N'Mirinda soda kem', N'LDV03', N'Lon', 143, 15000)
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [donViTinh], [soLuongTon], [giaBan]) VALUES (N'DV026', N'Thuốc mèo', N'LDV06', N'Gói', 52, 25000)
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [donViTinh], [soLuongTon], [giaBan]) VALUES (N'DV027', N'Thuốc 555', N'LDV06', N'Gói', 25, 55000)
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [donViTinh], [soLuongTon], [giaBan]) VALUES (N'DV029', N'Thuốc Sài Gòn', N'LDV06', N'Gói', 42, 22000)
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [donViTinh], [soLuongTon], [giaBan]) VALUES (N'DV030', N'Thuốc Richmond', N'LDV06', N'Gói', 32, 50000)
GO
INSERT [dbo].[HoaDon] ([maHoaDon], [maNhanVien], [maKhachHang], [maPhong], [thoiGianVao], [thoiGianRa], [tinhTrangHD], [khuyenMai]) VALUES (N'HD0000001', N'NV01', N'KH0009', N'P101', CAST(N'2023-10-25T19:46:10.007' AS DateTime), CAST(N'2023-10-25T22:49:47.027' AS DateTime), 1, N'')
INSERT [dbo].[HoaDon] ([maHoaDon], [maNhanVien], [maKhachHang], [maPhong], [thoiGianVao], [thoiGianRa], [tinhTrangHD], [khuyenMai]) VALUES (N'HD0000002', N'NV01', N'KH0019', N'P102', CAST(N'2023-10-29T18:20:15.717' AS DateTime), CAST(N'2023-10-29T22:49:52.543' AS DateTime), 1, N'')
INSERT [dbo].[HoaDon] ([maHoaDon], [maNhanVien], [maKhachHang], [maPhong], [thoiGianVao], [thoiGianRa], [tinhTrangHD], [khuyenMai]) VALUES (N'HD0000003', N'NV01', N'KH0017', N'P109', CAST(N'2023-11-01T14:15:23.043' AS DateTime), CAST(N'2023-11-01T22:49:56.183' AS DateTime), 1, N'')
INSERT [dbo].[HoaDon] ([maHoaDon], [maNhanVien], [maKhachHang], [maPhong], [thoiGianVao], [thoiGianRa], [tinhTrangHD], [khuyenMai]) VALUES (N'HD0000004', N'NV01', N'KH0003', N'P110', CAST(N'2023-11-04T10:35:28.867' AS DateTime), CAST(N'2023-11-04T22:50:00.310' AS DateTime), 1, N'')
INSERT [dbo].[HoaDon] ([maHoaDon], [maNhanVien], [maKhachHang], [maPhong], [thoiGianVao], [thoiGianRa], [tinhTrangHD], [khuyenMai]) VALUES (N'HD0000005', N'NV04', N'KH0004', N'P111', CAST(N'2023-11-05T20:57:26.187' AS DateTime), CAST(N'2023-11-05T22:58:38.673' AS DateTime), 1, N'')
INSERT [dbo].[HoaDon] ([maHoaDon], [maNhanVien], [maKhachHang], [maPhong], [thoiGianVao], [thoiGianRa], [tinhTrangHD], [khuyenMai]) VALUES (N'HD0000006', N'NV01', N'KH0002', N'P102', CAST(N'2023-11-07T18:12:36.083' AS DateTime), CAST(N'2023-11-07T19:40:00.343' AS DateTime), 1, N'')
INSERT [dbo].[HoaDon] ([maHoaDon], [maNhanVien], [maKhachHang], [maPhong], [thoiGianVao], [thoiGianRa], [tinhTrangHD], [khuyenMai]) VALUES (N'HD0000007', N'NV01', N'KH0018', N'P110', CAST(N'2023-11-07T19:47:58.403' AS DateTime), CAST(N'2023-11-07T19:48:05.127' AS DateTime), 1, N'')
INSERT [dbo].[HoaDon] ([maHoaDon], [maNhanVien], [maKhachHang], [maPhong], [thoiGianVao], [thoiGianRa], [tinhTrangHD], [khuyenMai]) VALUES (N'HD0000008', N'NV01', N'KH0001', N'P111', CAST(N'2023-11-07T19:49:01.753' AS DateTime), CAST(N'2023-11-07T19:49:46.447' AS DateTime), 1, N'')
INSERT [dbo].[HoaDon] ([maHoaDon], [maNhanVien], [maKhachHang], [maPhong], [thoiGianVao], [thoiGianRa], [tinhTrangHD], [khuyenMai]) VALUES (N'HD0000009', N'NV01', N'KH0016', N'P106', CAST(N'2023-11-08T14:35:46.757' AS DateTime), CAST(N'2023-11-08T20:19:57.267' AS DateTime), 1, N'')
INSERT [dbo].[HoaDon] ([maHoaDon], [maNhanVien], [maKhachHang], [maPhong], [thoiGianVao], [thoiGianRa], [tinhTrangHD], [khuyenMai]) VALUES (N'HD0000010', N'NV01', N'KH0004', N'P109', CAST(N'2023-11-11T23:54:05.497' AS DateTime), CAST(N'2023-11-12T00:22:08.907' AS DateTime), 1, N'')
INSERT [dbo].[HoaDon] ([maHoaDon], [maNhanVien], [maKhachHang], [maPhong], [thoiGianVao], [thoiGianRa], [tinhTrangHD], [khuyenMai]) VALUES (N'HD0000011', N'NV01', N'KH0015', N'P101', CAST(N'2023-11-12T10:27:12.207' AS DateTime), CAST(N'2023-11-12T12:00:04.513' AS DateTime), 1, N'')
INSERT [dbo].[HoaDon] ([maHoaDon], [maNhanVien], [maKhachHang], [maPhong], [thoiGianVao], [thoiGianRa], [tinhTrangHD], [khuyenMai]) VALUES (N'HD0000012', N'NV01', N'KH0001', N'P105', CAST(N'2023-11-12T12:31:31.010' AS DateTime), CAST(N'2023-11-12T14:33:50.543' AS DateTime), 1, N'')
INSERT [dbo].[HoaDon] ([maHoaDon], [maNhanVien], [maKhachHang], [maPhong], [thoiGianVao], [thoiGianRa], [tinhTrangHD], [khuyenMai]) VALUES (N'HD0000013', N'NV09', N'KH0019', N'P111', CAST(N'2023-11-13T19:31:48.840' AS DateTime), CAST(N'2023-11-13T22:18:53.333' AS DateTime), 1, N'KM')
INSERT [dbo].[HoaDon] ([maHoaDon], [maNhanVien], [maKhachHang], [maPhong], [thoiGianVao], [thoiGianRa], [tinhTrangHD], [khuyenMai]) VALUES (N'HD0000014', N'NV01', N'KH0012', N'P101', CAST(N'2023-11-15T13:48:59.700' AS DateTime), CAST(N'2023-11-15T14:14:43.743' AS DateTime), 1, N'')
INSERT [dbo].[HoaDon] ([maHoaDon], [maNhanVien], [maKhachHang], [maPhong], [thoiGianVao], [thoiGianRa], [tinhTrangHD], [khuyenMai]) VALUES (N'HD0000015', N'NV09', N'KH0016', N'P105', CAST(N'2023-11-25T18:00:59.113' AS DateTime), CAST(N'2023-11-25T20:23:57.213' AS DateTime), 1, N'')
INSERT [dbo].[HoaDon] ([maHoaDon], [maNhanVien], [maKhachHang], [maPhong], [thoiGianVao], [thoiGianRa], [tinhTrangHD], [khuyenMai]) VALUES (N'HD0000016', N'NV01', N'KH0012', N'P104', CAST(N'2023-11-27T10:55:46.437' AS DateTime), CAST(N'2023-11-27T12:09:10.953' AS DateTime), 1, N'')
INSERT [dbo].[HoaDon] ([maHoaDon], [maNhanVien], [maKhachHang], [maPhong], [thoiGianVao], [thoiGianRa], [tinhTrangHD], [khuyenMai]) VALUES (N'HD0000017', N'NV07', N'KH0014', N'P111', CAST(N'2023-12-11T19:39:56.937' AS DateTime), CAST(N'2023-12-11T22:11:07.197' AS DateTime), 1, N'')
INSERT [dbo].[HoaDon] ([maHoaDon], [maNhanVien], [maKhachHang], [maPhong], [thoiGianVao], [thoiGianRa], [tinhTrangHD], [khuyenMai]) VALUES (N'HD0000018', N'NV01', N'KH0017', N'P102', CAST(N'2023-12-14T20:28:20.810' AS DateTime), CAST(N'2023-12-14T20:52:10.387' AS DateTime), 1, N'')
INSERT [dbo].[HoaDon] ([maHoaDon], [maNhanVien], [maKhachHang], [maPhong], [thoiGianVao], [thoiGianRa], [tinhTrangHD], [khuyenMai]) VALUES (N'HD0000019', N'NV01', N'KH0005', N'P113', CAST(N'2023-12-14T20:43:14.240' AS DateTime), CAST(N'2023-12-14T21:07:49.190' AS DateTime), 1, N'')
GO
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKhachHang], [soDienThoai], [CCCD], [gioiTinh], [ngaySinh]) VALUES (N'KH0001', N'Hồ Ngọc Hà', N'0987898579', N'042388812199', 0, CAST(N'1992-12-24' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKhachHang], [soDienThoai], [CCCD], [gioiTinh], [ngaySinh]) VALUES (N'KH0002', N'Phạm Hoàng Thảo', N'0918951212', N'042435612199', 1, CAST(N'1996-11-23' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKhachHang], [soDienThoai], [CCCD], [gioiTinh], [ngaySinh]) VALUES (N'KH0003', N'Hà Thế Đức', N'0990512126', N'042765892199', 1, CAST(N'1998-04-21' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKhachHang], [soDienThoai], [CCCD], [gioiTinh], [ngaySinh]) VALUES (N'KH0004', N'Nguyễn Văn Bảo', N'0962017524', N'042355512349', 1, CAST(N'1993-01-12' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKhachHang], [soDienThoai], [CCCD], [gioiTinh], [ngaySinh]) VALUES (N'KH0005', N'Nguyễn Minh Ngọc', N'0967102427', N'042355523449', 0, CAST(N'2000-12-03' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKhachHang], [soDienThoai], [CCCD], [gioiTinh], [ngaySinh]) VALUES (N'KH0006', N'Phan Văn Trị', N'0927120529', N'042389026789', 1, CAST(N'2000-11-01' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKhachHang], [soDienThoai], [CCCD], [gioiTinh], [ngaySinh]) VALUES (N'KH0007', N'Lê Thị Hồng Nhung', N'0955520529', N'042312326789', 0, CAST(N'1997-08-03' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKhachHang], [soDienThoai], [CCCD], [gioiTinh], [ngaySinh]) VALUES (N'KH0008', N'Nguyễn Gia Anh', N'0955520521', N'042312321234', 1, CAST(N'1999-12-03' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKhachHang], [soDienThoai], [CCCD], [gioiTinh], [ngaySinh]) VALUES (N'KH0009', N'Nguyễn Văn An ', N'0977320529', N'042311286789', 1, CAST(N'1999-10-03' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKhachHang], [soDienThoai], [CCCD], [gioiTinh], [ngaySinh]) VALUES (N'KH0010', N'Nguyễn Bảo Trúc', N'0331205296', N'042311456189', 0, CAST(N'1996-07-23' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKhachHang], [soDienThoai], [CCCD], [gioiTinh], [ngaySinh]) VALUES (N'KH0011', N'Nguyễn Minh Khang', N'0315111296', N'042311456901', 1, CAST(N'1997-10-10' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKhachHang], [soDienThoai], [CCCD], [gioiTinh], [ngaySinh]) VALUES (N'KH0012', N'Hà Thị Phương Linh', N'0372644199', N'042303118999', 0, CAST(N'2003-10-07' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKhachHang], [soDienThoai], [CCCD], [gioiTinh], [ngaySinh]) VALUES (N'KH0013', N'Nguyễn Đình Dương', N'0986374957', N'042378994781', 1, CAST(N'2003-09-18' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKhachHang], [soDienThoai], [CCCD], [gioiTinh], [ngaySinh]) VALUES (N'KH0014', N'Nguyễn Quang Duy', N'0986389957', N'042378432781', 1, CAST(N'2003-02-23' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKhachHang], [soDienThoai], [CCCD], [gioiTinh], [ngaySinh]) VALUES (N'KH0015', N'Lương Quang Bình', N'0987238190', N'042387498112', 1, CAST(N'2001-04-20' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKhachHang], [soDienThoai], [CCCD], [gioiTinh], [ngaySinh]) VALUES (N'KH0016', N'Phạm Gia Hân', N'0987898123', N'042387898723', 0, CAST(N'1999-09-21' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKhachHang], [soDienThoai], [CCCD], [gioiTinh], [ngaySinh]) VALUES (N'KH0017', N'Phan Ngọc Huyền', N'0912381988', N'042384478912', 0, CAST(N'2001-11-25' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKhachHang], [soDienThoai], [CCCD], [gioiTinh], [ngaySinh]) VALUES (N'KH0018', N'Nguyễn Hữu Quốc', N'0991827365', N'042387912355', 1, CAST(N'2002-12-19' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKhachHang], [soDienThoai], [CCCD], [gioiTinh], [ngaySinh]) VALUES (N'KH0019', N'Tô Gia Châu', N'0998999898', N'042333322299', 0, CAST(N'1999-11-13' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKhachHang], [soDienThoai], [CCCD], [gioiTinh], [ngaySinh]) VALUES (N'KH0020', N'Võ Tú Bình', N'0988127300', N'042387849212', 1, CAST(N'1992-11-12' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKhachHang], [soDienThoai], [CCCD], [gioiTinh], [ngaySinh]) VALUES (N'KH0021', N'Gin Tuấn Kiệt', N'0372644198', N'', 1, CAST(N'1994-10-02' AS Date))
GO
INSERT [dbo].[LoaiDichVu] ([maLoaiDichVu], [tenLoaiDichVu]) VALUES (N'LDV01', N'Trái cây')
INSERT [dbo].[LoaiDichVu] ([maLoaiDichVu], [tenLoaiDichVu]) VALUES (N'LDV02', N'Đồ uống có cồn')
INSERT [dbo].[LoaiDichVu] ([maLoaiDichVu], [tenLoaiDichVu]) VALUES (N'LDV03', N'Đồ uống không cồn')
INSERT [dbo].[LoaiDichVu] ([maLoaiDichVu], [tenLoaiDichVu]) VALUES (N'LDV04', N'Đồ ăn vặt')
INSERT [dbo].[LoaiDichVu] ([maLoaiDichVu], [tenLoaiDichVu]) VALUES (N'LDV05', N'Món được chế biến')
INSERT [dbo].[LoaiDichVu] ([maLoaiDichVu], [tenLoaiDichVu]) VALUES (N'LDV06', N'Thuốc lá')
GO
INSERT [dbo].[LoaiPhong] ([maLoaiPhong], [tenLoaiPhong], [sucChua]) VALUES (N'PL', N'Phòng lớn', 20)
INSERT [dbo].[LoaiPhong] ([maLoaiPhong], [tenLoaiPhong], [sucChua]) VALUES (N'PN', N'Phòng nhỏ', 5)
INSERT [dbo].[LoaiPhong] ([maLoaiPhong], [tenLoaiPhong], [sucChua]) VALUES (N'PV', N'Phòng vừa', 10)
INSERT [dbo].[LoaiPhong] ([maLoaiPhong], [tenLoaiPhong], [sucChua]) VALUES (N'VIP', N'Phòng VIP', 15)
GO
INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [soDienThoai], [CCCD], [gioiTinh], [ngaySinh], [diaChi], [chucVu], [trangThai], [taiKhoan]) VALUES (N'NV01', N'Mai Thái Vũ', N'0973774811', N'042388844499', 1, CAST(N'2001-01-01' AS Date), N'Bình Thạnh', N'Quản lý', N'Đang làm', N'maithaivu')
INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [soDienThoai], [CCCD], [gioiTinh], [ngaySinh], [diaChi], [chucVu], [trangThai], [taiKhoan]) VALUES (N'NV02', N'Đặng Nguyên Vũ', N'0715344673', N'042311189898', 1, CAST(N'1996-12-23' AS Date), N'Bình Tân', N'Nhân viên', N'Đang làm', N'dangnguyenvu')
INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [soDienThoai], [CCCD], [gioiTinh], [ngaySinh], [diaChi], [chucVu], [trangThai], [taiKhoan]) VALUES (N'NV03', N'Hoàng Gia Bảo', N'0998234777', N'042303001234', 1, CAST(N'2001-11-21' AS Date), N'Phú Nhuận', N'Nhân viên', N'Đã nghỉ', N'hoanggiabao')
INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [soDienThoai], [CCCD], [gioiTinh], [ngaySinh], [diaChi], [chucVu], [trangThai], [taiKhoan]) VALUES (N'NV04', N'Lâm Bảo Châu', N'0978222789', N'042312345555', 1, CAST(N'1996-10-11' AS Date), N'Quận 3', N'Nhân viên', N'Đang làm', N'lambaochau')
INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [soDienThoai], [CCCD], [gioiTinh], [ngaySinh], [diaChi], [chucVu], [trangThai], [taiKhoan]) VALUES (N'NV05', N'Lã Minh Ngọc', N'0977678345', N'042388887651', 0, CAST(N'1999-06-23' AS Date), N'Gò Vấp', N'Nhân viên', N'Đang làm', N'laminhngoc')
INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [soDienThoai], [CCCD], [gioiTinh], [ngaySinh], [diaChi], [chucVu], [trangThai], [taiKhoan]) VALUES (N'NV06', N'Lê Thái Cường', N'0932344647', N'043277776543', 1, CAST(N'2000-01-21' AS Date), N'Bình Dương', N'Nhân viên', N'Đang làm', N'lethaicuong')
INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [soDienThoai], [CCCD], [gioiTinh], [ngaySinh], [diaChi], [chucVu], [trangThai], [taiKhoan]) VALUES (N'NV07', N'Mai Kim Ngọc', N'0912345671', N'043211115839', 0, CAST(N'1999-02-14' AS Date), N'Bình Thạnh', N'Nhân viên', N'Đang làm', N'maikimngoc')
INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [soDienThoai], [CCCD], [gioiTinh], [ngaySinh], [diaChi], [chucVu], [trangThai], [taiKhoan]) VALUES (N'NV08', N'Bùi Bích Trâm', N'0932234347', N'042377778888', 1, CAST(N'1992-01-11' AS Date), N'Bình Thạnh', N'Nhân viên', N'Đang làm', N'buibichtram')
INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [soDienThoai], [CCCD], [gioiTinh], [ngaySinh], [diaChi], [chucVu], [trangThai], [taiKhoan]) VALUES (N'NV09', N'Mã Tố Huyên', N'0911343678', N'042384905488', 0, CAST(N'1999-07-10' AS Date), N'Quận 4', N'Nhân viên', N'Đang làm', N'matohuyen')
INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [soDienThoai], [CCCD], [gioiTinh], [ngaySinh], [diaChi], [chucVu], [trangThai], [taiKhoan]) VALUES (N'NV10', N'Nguyễn Gia Vũ', N'0913240672', N'042389036748', 1, CAST(N'2000-01-01' AS Date), N'Phú Nhuận', N'Nhân viên', N'Đang làm', N'nguyengiavu')
INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [soDienThoai], [CCCD], [gioiTinh], [ngaySinh], [diaChi], [chucVu], [trangThai], [taiKhoan]) VALUES (N'NV11', N'Nguyễn Thái Bảo', N'0312144673', N'042367437771', 1, CAST(N'2002-12-20' AS Date), N'Bình Tân', N'Nhân Viên', N'Đang làm', N'nguyenthaibao')
GO
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [tinhTrang], [viTri], [giaPhong]) VALUES (N'P101', N'VIP', N'Trống', N'Tầng trệt', 350000)
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [tinhTrang], [viTri], [giaPhong]) VALUES (N'P102', N'PL', N'Trống', N'Tầng trệt', 230000)
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [tinhTrang], [viTri], [giaPhong]) VALUES (N'P103', N'PL', N'Trống', N'Tầng trệt', 230000)
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [tinhTrang], [viTri], [giaPhong]) VALUES (N'P104', N'VIP', N'Trống', N'Tầng 1', 350000)
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [tinhTrang], [viTri], [giaPhong]) VALUES (N'P105', N'PN', N'Trống', N'Tầng 1', 150000)
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [tinhTrang], [viTri], [giaPhong]) VALUES (N'P106', N'PN', N'Trống', N'Tầng 1', 150000)
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [tinhTrang], [viTri], [giaPhong]) VALUES (N'P107', N'PL', N'Trống', N'Tầng 1', 230000)
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [tinhTrang], [viTri], [giaPhong]) VALUES (N'P108', N'VIP', N'Trống', N'Tầng 2', 350000)
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [tinhTrang], [viTri], [giaPhong]) VALUES (N'P109', N'PL', N'Trống', N'Tầng 2', 230000)
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [tinhTrang], [viTri], [giaPhong]) VALUES (N'P110', N'PV', N'Trống', N'Tầng 2', 180000)
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [tinhTrang], [viTri], [giaPhong]) VALUES (N'P111', N'PN', N'Trống', N'Tầng 2', 150000)
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [tinhTrang], [viTri], [giaPhong]) VALUES (N'P112', N'PV', N'Trống', N'Tầng 3', 180000)
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [tinhTrang], [viTri], [giaPhong]) VALUES (N'P113', N'PL', N'Trống', N'Tầng 3', 230000)
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [tinhTrang], [viTri], [giaPhong]) VALUES (N'P114', N'PL', N'Trống', N'Tầng 3', 230000)
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [tinhTrang], [viTri], [giaPhong]) VALUES (N'P115', N'PN', N'Trống', N'Tầng 3', 150000)
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [tinhTrang], [viTri], [giaPhong]) VALUES (N'P116', N'PV', N'Trống', N'Tầng 4', 180000)
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [tinhTrang], [viTri], [giaPhong]) VALUES (N'P117', N'PL', N'Trống', N'Tầng 4', 230000)
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [tinhTrang], [viTri], [giaPhong]) VALUES (N'P118', N'PL', N'Trống', N'Tầng 4', 230000)
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [tinhTrang], [viTri], [giaPhong]) VALUES (N'P119', N'PN', N'Trống', N'Tầng 4', 150000)
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [tinhTrang], [viTri], [giaPhong]) VALUES (N'P120', N'PN', N'Trống', N'Tầng 4', 150000)
GO
INSERT [dbo].[TaiKhoan] ([taiKhoan], [matKhau], [tinhTrang]) VALUES (N'buibichtram', N'1', 1)
INSERT [dbo].[TaiKhoan] ([taiKhoan], [matKhau], [tinhTrang]) VALUES (N'dangnguyenvu', N'1', 1)
INSERT [dbo].[TaiKhoan] ([taiKhoan], [matKhau], [tinhTrang]) VALUES (N'hoanggiabao', N'1', 0)
INSERT [dbo].[TaiKhoan] ([taiKhoan], [matKhau], [tinhTrang]) VALUES (N'lambaochau', N'1', 1)
INSERT [dbo].[TaiKhoan] ([taiKhoan], [matKhau], [tinhTrang]) VALUES (N'laminhngoc', N'1', 1)
INSERT [dbo].[TaiKhoan] ([taiKhoan], [matKhau], [tinhTrang]) VALUES (N'lethaicuong', N'1', 1)
INSERT [dbo].[TaiKhoan] ([taiKhoan], [matKhau], [tinhTrang]) VALUES (N'maikimngoc', N'1', 1)
INSERT [dbo].[TaiKhoan] ([taiKhoan], [matKhau], [tinhTrang]) VALUES (N'maithaivu', N'1', 1)
INSERT [dbo].[TaiKhoan] ([taiKhoan], [matKhau], [tinhTrang]) VALUES (N'matohuyen', N'1', 1)
INSERT [dbo].[TaiKhoan] ([taiKhoan], [matKhau], [tinhTrang]) VALUES (N'nguyengiavu', N'1', 1)
INSERT [dbo].[TaiKhoan] ([taiKhoan], [matKhau], [tinhTrang]) VALUES (N'nguyenthaibao', N'1', 1)
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__NhanVien__B4C453187BD25935]    Script Date: 12/14/2023 9:55:03 PM ******/
ALTER TABLE [dbo].[NhanVien] ADD  CONSTRAINT [UQ__NhanVien__B4C453187BD25935] UNIQUE NONCLUSTERED 
(
	[taiKhoan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[ChiTietDichVu] ADD  CONSTRAINT [DF__CTDichVu__soLuon__3C69FB99]  DEFAULT ((1)) FOR [soLuong]
GO
ALTER TABLE [dbo].[DichVu] ADD  CONSTRAINT [DF__DichVu__soLuongT__30F848ED]  DEFAULT ((0)) FOR [soLuongTon]
GO
ALTER TABLE [dbo].[DichVu] ADD  CONSTRAINT [DF__DichVu__giaBan__2F10007B]  DEFAULT ((0)) FOR [giaBan]
GO
ALTER TABLE [dbo].[HoaDon] ADD  CONSTRAINT [DF__HoaDon__ngayGioD__4316F928]  DEFAULT (getdate()) FOR [thoiGianVao]
GO
ALTER TABLE [dbo].[HoaDon] ADD  CONSTRAINT [DF__HoaDon__tinhTran__440B1D61]  DEFAULT ((0)) FOR [tinhTrangHD]
GO
ALTER TABLE [dbo].[LoaiPhong] ADD  CONSTRAINT [DF__LoaiPhong__sucCh__34C8D9D1]  DEFAULT ((0)) FOR [sucChua]
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
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietHoaDon_Phong1] FOREIGN KEY([maPhong])
REFERENCES [dbo].[Phong] ([maPhong])
GO
ALTER TABLE [dbo].[ChiTietHoaDon] CHECK CONSTRAINT [FK_ChiTietHoaDon_Phong1]
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
ALTER TABLE [dbo].[LoaiPhong]  WITH CHECK ADD  CONSTRAINT [CK__LoaiPhong__sucCh__00200768] CHECK  (([sucChua]>(0)))
GO
ALTER TABLE [dbo].[LoaiPhong] CHECK CONSTRAINT [CK__LoaiPhong__sucCh__00200768]
GO
ALTER TABLE [dbo].[LoaiPhong]  WITH CHECK ADD  CONSTRAINT [CK__LoaiPhong__sucCh__35BCFE0A] CHECK  (([sucChua]>(0)))
GO
ALTER TABLE [dbo].[LoaiPhong] CHECK CONSTRAINT [CK__LoaiPhong__sucCh__35BCFE0A]
GO
